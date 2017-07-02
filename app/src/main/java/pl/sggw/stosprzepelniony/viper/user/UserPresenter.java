package pl.sggw.stosprzepelniony.viper.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UserPresenter
        extends BaseRxPresenter
        <UserContract.View,
                UserContract.Interactor,
                UserContract.Routing>
        implements ViperPresenter<UserContract.View> {

    UserPresenter(Bundle args) {
        super(args);
    }

    @Override
    public void attachView(UserContract.View view) {
        super.attachView(view);

        addSubscription(
                Single
                        .just(Irrelevant.EVENT)
                        .filter(event -> isViewAttached())
                        .flatMapObservable(event -> getInteractor().getAdsByUserId(getArgs().getInt(UserContract.View.USER_ID_BUNDLE)))
                        .zipWith(getInteractor().getUserInfoById(getArgs().getInt(UserContract.View.USER_ID_BUNDLE)), Pair::new)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(pair -> {
                            getView().showUserInfo(pair.second);
                            if (pair.first.isEmpty())
                                getView().showEmptyState();
                            else
                                getView().showContent(pair.first);
                        }, error -> getView().showError(error)));
        addSubscription(
                getView()
                        .getListItemClicks()
                        .filter(event -> isViewAttached())
                        .subscribe(ad -> getRouting().startAdDetailsActivity(ad)));
    }

    @NonNull
    @Override
    public UserContract.Routing createRouting() {
        return new UserRouting();
    }

    @NonNull
    @Override
    public UserContract.Interactor createInteractor() {
        return new UserInteractor();
    }
}
