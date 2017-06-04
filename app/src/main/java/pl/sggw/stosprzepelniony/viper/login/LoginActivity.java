package pl.sggw.stosprzepelniony.viper.login;


import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;

public class LoginActivity
        extends ViperButterKnifePassiveActivity
        <LoginContract.View>
        implements LoginContract.View {


    @NonNull
    @Override
    public ViperPresenter<LoginContract.View> createPresenter() {
        return new LoginPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
