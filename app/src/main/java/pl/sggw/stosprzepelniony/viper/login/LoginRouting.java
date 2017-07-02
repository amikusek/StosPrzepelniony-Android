package pl.sggw.stosprzepelniony.viper.login;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.viper.main.MainActivity;
import pl.sggw.stosprzepelniony.viper.register.RegisterActivity;
import pl.sggw.stosprzepelniony.viper.reset_password.ResetPasswordActivity;

class LoginRouting
        extends BaseRxRouting<Activity>
        implements LoginContract.Routing {

    @Override
    public void startMainActivity() {
        if (isContextAttached()) {
            MainActivity.start(getRelatedContext());
            getRelatedContext().finishAffinity();
        }
    }

    @Override
    public void startResetPasswordActivity() {
        if (isContextAttached()) ResetPasswordActivity.start(getRelatedContext());
    }

    @Override
    public void startSignUpActivity() {
        if (isContextAttached()) RegisterActivity.start(getRelatedContext());
    }
}
