package pl.sggw.stosprzepelniony.viper.user;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

public class UserPresenter
        extends BaseRxPresenter
        <UserContract.View,
                UserContract.Interactor,
                UserContract.Routing>
        implements ViperPresenter<UserContract.View> {

    @Override
    public void attachView(UserContract.View view) {
        super.attachView(view);

        addSubscription(
                Single
                        .just(Irrelevant.EVENT)
                        .filter(event -> isViewAttached())
                        .flatMapObservable(event -> getInteractor().getAdsByUserId(5))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userAds -> {
                            if (userAds.isEmpty())
                                getView().showEmptyState();
                            else
                                getView().showContent(userAds);
                        }, error -> getView().showError(error)));
        addSubscription(
                Single
                        .just(Irrelevant.EVENT)
                        .filter(event -> isViewAttached())
                        .flatMapObservable(event -> getInteractor().getUserInfoById(5))
                        .subscribe(user -> getView().showUserInfo(user), error -> getView().showError(error))
        );
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
