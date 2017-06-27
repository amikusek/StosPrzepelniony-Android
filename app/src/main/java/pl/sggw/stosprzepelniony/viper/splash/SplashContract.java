package pl.sggw.stosprzepelniony.viper.splash;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import io.reactivex.Completable;

interface SplashContract {

    interface View extends MvpView {

    }

    interface Interactor extends ViperRxInteractor {

        Completable checkUserSessionStatus();
        Boolean userHasEmptySessionToken();
    }

    interface Routing extends ViperRxRouting<Activity> {

        void startLoginScreen();
        void startMainScreen();
    }
}
