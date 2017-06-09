package pl.sggw.stosprzepelniony.viper.main;


import android.support.annotation.NonNull;

import pl.sggw.stosprzepelniony.R;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.ViperAiPassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class MainActivity
        extends ViperAiPassiveActivity
        <MainContract.View>
        implements MainContract.View {


    @NonNull
    @Override
    public ViperPresenter<MainContract.View> createPresenter() {
        return new MainPresenter();
    }


    @Override
    protected void injectViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
