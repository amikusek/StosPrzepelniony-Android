package pl.sggw.stosprzepelniony.viper.ad_list;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.viper.new_advertisement.NewAdvertisementActivity;

class AdListRouting
        extends BaseRxRouting<Activity>
        implements AdListContract.Routing {

    @Override
    public void startNewAdActivity() {
        NewAdvertisementActivity.start(getRelatedContext());
    }
}
