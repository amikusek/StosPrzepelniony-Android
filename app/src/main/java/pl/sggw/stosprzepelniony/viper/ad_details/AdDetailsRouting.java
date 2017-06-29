package pl.sggw.stosprzepelniony.viper.ad_details;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

class AdDetailsRouting
        extends BaseRxRouting<Activity>
        implements AdDetailsContract.Routing {

    @Override
    public void closeScreen() {
        if (isContextAttached()) getRelatedContext().finish();
    }
}
