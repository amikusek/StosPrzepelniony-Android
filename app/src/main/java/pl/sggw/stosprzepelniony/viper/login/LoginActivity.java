package pl.sggw.stosprzepelniony.viper.login;


import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.LoginBundle;

public class LoginActivity
        extends ViperButterKnifePassiveActivity
        <LoginContract.View>
        implements LoginContract.View {

    @Override
    public Observable<LoginBundle> getLoginClicks() {
        return null;
    }

    @Override
    public Observable<Void> getForgottenPasswordClicks() {
        return null;
    }

    @Override
    public Observable<Void> getSignUpClicks() {
        return null;
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @NonNull
    @Override
    public ViperPresenter<LoginContract.View> createPresenter() {
        return new LoginPresenter();
    }
}
