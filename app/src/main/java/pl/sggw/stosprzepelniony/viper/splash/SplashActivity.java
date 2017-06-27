package pl.sggw.stosprzepelniony.viper.splash;


import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;

public class SplashActivity
        extends ViperButterKnifePassiveActivity
        <SplashContract.View>
        implements SplashContract.View {

    @NonNull
    @Override
    public ViperPresenter<SplashContract.View> createPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
