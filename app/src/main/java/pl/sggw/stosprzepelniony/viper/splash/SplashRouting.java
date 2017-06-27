package pl.sggw.stosprzepelniony.viper.splash;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.viper.login.LoginActivity;
import pl.sggw.stosprzepelniony.viper.main.MainActivity;

class SplashRouting
        extends BaseRxRouting<Activity>
        implements SplashContract.Routing {

    @Override
    public void startLoginScreen() {
        if (isContextAttached()) LoginActivity.start(getRelatedContext());
    }

    @Override
    public void startMainScreen() {
        if (isContextAttached()) MainActivity.start(getRelatedContext());
    }
}
