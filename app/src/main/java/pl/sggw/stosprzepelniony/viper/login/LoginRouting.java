package pl.sggw.stosprzepelniony.viper.login;

import android.app.Activity;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

class LoginRouting
        extends BaseRxRouting<Activity>
        implements LoginContract.Routing {

    @Override
    public void startMainActivity() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start MainActivity here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startResetPasswordActivity() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start ResetPasswordActivity here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startSignUpActivity() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start SignUpActivity here", Toast.LENGTH_SHORT).show();
        }
    }
}
