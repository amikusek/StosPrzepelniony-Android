package pl.sggw.stosprzepelniony.viper.new_advertisement;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

class NewAdvertisementRouting
        extends BaseRxRouting<Activity>
        implements NewAdvertisementContract.Routing {

    @Override
    public void closeScreen() {
        if (isContextAttached()) getRelatedContext().finish();
    }
}
