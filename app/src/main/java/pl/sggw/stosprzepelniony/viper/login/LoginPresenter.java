package pl.sggw.stosprzepelniony.viper.login;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class LoginPresenter
        extends BaseRxPresenter
        <LoginContract.View,
                LoginContract.Interactor,
                LoginContract.Routing>
        implements ViperPresenter<LoginContract.View> {

    @NonNull
    @Override
    public LoginContract.Routing createRouting() {
        return new LoginRouting();
    }

    @NonNull
    @Override
    public LoginContract.Interactor createInteractor() {
        return new LoginInteractor();
    }
}
