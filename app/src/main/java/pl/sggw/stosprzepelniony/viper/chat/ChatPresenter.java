package pl.sggw.stosprzepelniony.viper.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.data.entity.ChatMessageBundle;
import pl.sggw.stosprzepelniony.exception.EmptyMessageException;
import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withObservableRetryErrorLogic;

public class ChatPresenter
        extends BaseRxPresenter
        <ChatContract.View,
                ChatContract.Interactor,
                ChatContract.Routing>
        implements ViperPresenter<ChatContract.View> {

    private PublishSubject<Object> refreshMessagesSubject = PublishSubject.create();

    ChatPresenter(Bundle args) {
        super(args);
    }

    @Override
    public void attachView(ChatContract.View view) {
        super.attachView(view);

        addSubscription(
                refreshMessagesSubject
                        .filter(event -> isViewAttached())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(event -> getView().showLoading())
                        .flatMap(event -> getInteractor().getMessagesFromUserById(
                                getArgs().getInt(ChatContract.View.AD_ID_BUNDLE),
                                getArgs().getInt(ChatContract.View.USER_ID_BUNDLE)))
                        .observeOn(Schedulers.computation())
                        .concatMap(messages ->
                                Observable.fromIterable(messages).sorted((o1, o2) ->
                                        o1.getDate().compareTo(o2.getDate())
                                ).toList().toObservable())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withObservableRetryErrorLogic(error -> getView().showError(error, false)))
                        .subscribe(messages -> {
                                    if (messages.isEmpty())
                                        getView().showEmptyState();
                                    else
                                        getView().showContent(messages);
                                }));
        addSubscription(
                getView()
                        .getSendButtonClicks()
                        .filter(event -> isViewAttached())
                        .doOnNext(chatMessageBundle -> getView().showLoading())
                        .doOnNext(this::validateMessageLength)
                        .flatMapSingle(bundle -> getInteractor().sendMessage(bundle).toSingleDefault(bundle))
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withObservableRetryErrorLogic(error -> getView().showError(error, false)))
                        .subscribe(chatMessageBundle -> {
                            getView().updateView();
                            refreshMessagesSubject.onNext(Irrelevant.EVENT);
                        }));
        addSubscription(
                getView()
                        .getBackButtonClicks()
                        .filter(event -> isViewAttached())
                        .subscribe(event -> getRouting().closeScreen()));

        refreshMessagesSubject.onNext(Irrelevant.EVENT);
        addSubscription(
                Observable
                        .interval(10, TimeUnit.SECONDS)
                        .map(event -> Irrelevant.EVENT)
                        .doOnNext(refreshMessagesSubject::onNext)
                        .subscribe());
    }

    private void validateMessageLength(ChatMessageBundle bundle) throws EmptyMessageException {
        if (bundle.getMessage().isEmpty()) throw new EmptyMessageException();
    }

    @NonNull
    @Override
    public ChatContract.Routing createRouting() {
        return new ChatRouting();
    }

    @NonNull
    @Override
    public ChatContract.Interactor createInteractor() {
        return new ChatInteractor();
    }
}
