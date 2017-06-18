package pl.sggw.stosprzepelniony.viper.register;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

class RegisterRouting
        extends BaseRxRouting<Activity>
        implements RegisterContract.Routing {

    @Override
    public void closeScreen() {
        if (isContextAttached()) getRelatedContext().finish();
    }
}
