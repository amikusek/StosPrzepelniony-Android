package pl.sggw.stosprzepelniony.viper.reset_password;


import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;

public class ResetPasswordActivity
        extends ViperButterKnifePassiveActivity
        <ResetPasswordContract.View>
        implements ResetPasswordContract.View {


    @NonNull
    @Override
    public ViperPresenter<ResetPasswordContract.View> createPresenter() {
        return new ResetPasswordPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }
}
