package pl.sggw.stosprzepelniony.viper.main;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class MainPresenter
        extends BaseRxPresenter
        <MainContract.View,
                MainContract.Interactor,
                MainContract.Routing>
        implements ViperPresenter<MainContract.View> {

    @NonNull
    @Override
    public MainContract.Routing createRouting() {
        return new MainRouting();
    }

    @NonNull
    @Override
    public MainContract.Interactor createInteractor() {
        return new MainInteractor();
    }
}
