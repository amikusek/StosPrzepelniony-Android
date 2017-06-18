package pl.sggw.stosprzepelniony.viper.reset_password;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import io.reactivex.Completable;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.ResetPasswordBundle;

interface ResetPasswordContract {


    interface View extends MvpView {
        Observable<ResetPasswordBundle> getResetPasswordClicks();
        Observable<Object> getCloseActivityClicks();
        void showLoading();
        void showError(Throwable throwable);
        void showResetPasswordSuccess();
    }

    interface Interactor extends ViperRxInteractor {
        Completable performPasswordRestoring(ResetPasswordBundle resetPasswordBundle);
    }

    interface Routing extends ViperRxRouting<Activity> {
        void closeScreen();
    }
}
