package pl.sggw.stosprzepelniony.viper.splash;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class SplashPresenter
        extends BaseRxPresenter
        <SplashContract.View,
                SplashContract.Interactor,
                SplashContract.Routing>
        implements ViperPresenter<SplashContract.View> {

    @NonNull
    @Override
    public SplashContract.Routing createRouting() {
        return new SplashRouting();
    }

    @NonNull
    @Override
    public SplashContract.Interactor createInteractor() {
        return new SplashInteractor();
    }
}
