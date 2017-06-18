package pl.sggw.stosprzepelniony.viper.register;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import io.reactivex.Completable;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.RegisterBundle;

interface RegisterContract {

    interface Presenter extends ViperPresenter<View> {

    }

    interface View extends MvpView {
        Observable<RegisterBundle> getRegisterClicks();
        Observable<Object> getCloseActivityClicks();
        void showLoading();
        void showError(Throwable throwable);
        void showRegistrationSuccess();
    }

    interface Interactor extends ViperRxInteractor {
        Completable performRegistration(RegisterBundle registerBundle);
    }

    interface Routing extends ViperRxRouting<Activity> {
        void closeScreen();
    }
}
