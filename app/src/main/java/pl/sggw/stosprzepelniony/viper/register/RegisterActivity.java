package pl.sggw.stosprzepelniony.viper.register;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;

public class RegisterActivity
        extends ViperButterKnifePassiveActivity<RegisterContract.View>
        implements RegisterContract.View {

    @NonNull
    @Override
    public ViperPresenter<RegisterContract.View> createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
