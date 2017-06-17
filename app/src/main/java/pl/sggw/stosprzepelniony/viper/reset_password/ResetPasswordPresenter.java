package pl.sggw.stosprzepelniony.viper.reset_password;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class ResetPasswordPresenter
        extends BaseRxPresenter
        <ResetPasswordContract.View,
                ResetPasswordContract.Interactor,
                ResetPasswordContract.Routing>
        implements ViperPresenter<ResetPasswordContract.View> {

    @NonNull
    @Override
    public ResetPasswordContract.Routing createRouting() {
        return new ResetPasswordRouting();
    }

    @NonNull
    @Override
    public ResetPasswordContract.Interactor createInteractor() {
        return new ResetPasswordInteractor();
    }
}
