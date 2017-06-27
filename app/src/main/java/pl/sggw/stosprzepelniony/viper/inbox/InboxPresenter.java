package pl.sggw.stosprzepelniony.viper.inbox;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withObservableRetryErrorLogic;

public class InboxPresenter
        extends BaseRxPresenter
        <InboxContract.View,
                InboxContract.Interactor,
                InboxContract.Routing>
        implements ViperPresenter<InboxContract.View> {

    @Override
    public void attachView(InboxContract.View view) {
        super.attachView(view);

        addSubscription(
                Single
                        .just(Irrelevant.EVENT)
                        .filter(event -> isViewAttached())
                        .doOnSuccess(event -> getView().showLoading())
                        .flatMapObservable(event -> getInteractor().getMessages())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withObservableRetryErrorLogic(getView()::showError))
                        .subscribe(messageListItems -> {
                            if (messageListItems.isEmpty())
                                getView().showEmptyState();
                            else
                                getView().setMessagesList(messageListItems);
                        }));
        addSubscription(
                getView()
                        .getListItemClicks()
                        .filter(event -> isViewAttached())
                        .compose(withObservableRetryErrorLogic(getView()::showError))
                        .subscribe(messageBundle -> getRouting().startChatActivity(messageBundle)));
    }

    @NonNull
    @Override
    public InboxContract.Routing createRouting() {
        return new InboxRouting();
    }

    @NonNull
    @Override
    public InboxContract.Interactor createInteractor() {
        return new InboxInteractor();
    }
}
