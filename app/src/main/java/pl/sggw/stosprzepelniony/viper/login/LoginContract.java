package pl.sggw.stosprzepelniony.viper.login;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.data.result.LoginResult;

import io.reactivex.Observable;

interface LoginContract {

    interface View extends MvpView {

        Observable<LoginBundle> getLoginClicks();
        Observable<Object> getForgottenPasswordClicks();
        Observable<Object> getSignUpClicks();
        Observable<Boolean> getAutoSignInClicks();
        void showError(Throwable throwable);
        void showLoading();
    }

    interface Interactor extends ViperRxInteractor {

        Observable<LoginResult> performLogin(LoginBundle loginBundle);
        void saveSingInMode(boolean enabled);
    }

    interface Routing extends ViperRxRouting<Activity> {

        void startMainActivity();
        void startResetPasswordActivity();
        void startSignUpActivity();
    }
}
