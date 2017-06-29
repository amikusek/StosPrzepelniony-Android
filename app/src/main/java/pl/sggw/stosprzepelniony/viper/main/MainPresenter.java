package pl.sggw.stosprzepelniony.viper.main;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.util.constant.NavigationItem;

public class MainPresenter
        extends BaseRxPresenter
        <MainContract.View,
                MainContract.Interactor,
                MainContract.Routing>
        implements ViperPresenter<MainContract.View> {


    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        if (isViewAttached()) {
            addSubscription(
                    getView()
                            .getNavigationDrawerClicks()
                            .doOnNext(event -> {
                                if (event.getNavigationItem() == NavigationItem.LOGOUT)
                                    getInteractor().logout();
                            })
                            .doOnError(error -> getView().showError(error))
                            .retry()
                            .subscribe(event -> {
                                        getRouting().startProperScreenForNavigationEvent(event);
                                        getView().closeDrawer();
                                    }));
        }

        getRouting().replaceByAdvertisementsFragment();
    }

    @NonNull
    @Override
    public MainContract.Routing createRouting() {
        return new MainRouting();
    }

    @NonNull
    @Override
    public MainContract.Interactor createInteractor() {
        return new MainInteractor();
    }
}
