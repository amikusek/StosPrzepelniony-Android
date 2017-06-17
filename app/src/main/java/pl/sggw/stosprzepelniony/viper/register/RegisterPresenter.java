package pl.sggw.stosprzepelniony.viper.register;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;

public class RegisterPresenter
        extends BaseRxPresenter
        <RegisterContract.View,
                RegisterContract.Interactor,
                RegisterContract.Routing>
        implements RegisterContract.Presenter {

    @NonNull
    @Override
    public RegisterContract.Routing createRouting() {
        return new RegisterRouting();
    }

    @NonNull
    @Override
    public RegisterContract.Interactor createInteractor() {
        return new RegisterInteractor();
    }
}
