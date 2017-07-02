package pl.sggw.stosprzepelniony.viper.user;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.viper.ad_details.AdDetailsActivity;

class UserRouting
        extends BaseRxRouting<Activity>
        implements UserContract.Routing {

    @Override
    public void startAdDetailsActivity(Ad ad) {
        if (isContextAttached()) AdDetailsActivity.start(getRelatedContext(), ad.getId());
    }
}
