package pl.sggw.stosprzepelniony.viper.login;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.data.result.LoginResult;

interface LoginContract {

    interface View extends MvpView {
        Observable<LoginBundle> getLoginClicks();
        Observable<Void> getForgottenPasswordClicks();
        Observable<Void> getSignUpClicks();
        void showError(Throwable throwable);
        void showLoading();
    }

    interface Interactor extends ViperRxInteractor {
        Observable<LoginResult> performLogin(LoginBundle loginBundle);
    }

    interface Routing extends ViperRxRouting<Activity> {
        void startMainActivity();
    }
}
