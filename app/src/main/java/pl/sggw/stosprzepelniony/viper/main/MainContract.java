package pl.sggw.stosprzepelniony.viper.main;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import pl.sggw.stosprzepelniony.data.event.NavigationDrawerItemSelectedEvent;

import io.reactivex.Observable;

interface MainContract {


    interface View extends MvpView {
        void closeDrawer();
        Observable<NavigationDrawerItemSelectedEvent> getNavigationDrawerClicks();
        void showError(Throwable throwable);
        void showToast(String text);
    }

    interface Interactor extends ViperRxInteractor {
        void logout();
    }

    interface Routing extends ViperRxRouting<Activity> {
        void startProperScreenForNavigationEvent(NavigationDrawerItemSelectedEvent event, int userId);
        void startInboxActivity();
        void startUserProfileActivity(int userId);
        void startSettingsActivity();
        void startLoginActivity();
        void replaceByAdvertisementsFragment();
        void replaceByCategoriesFragment();
    }
}
