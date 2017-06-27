package pl.sggw.stosprzepelniony.viper.user;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class UserPresenter
        extends BaseRxPresenter
        <UserContract.View,
                UserContract.Interactor,
                UserContract.Routing>
        implements ViperPresenter<UserContract.View> {

    @NonNull
    @Override
    public UserContract.Routing createRouting() {
        return new UserRouting();
    }

    @NonNull
    @Override
    public UserContract.Interactor createInteractor() {
        return new UserInteractor();
    }
}
