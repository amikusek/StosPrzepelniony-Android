package pl.sggw.stosprzepelniony.viper.user;

import android.app.Activity;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.data.entity.Ad;

class UserRouting
        extends BaseRxRouting<Activity>
        implements UserContract.Routing {

    @Override
    public void startAdDetailsActivity(Ad ad) {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start DetailsActivity here: id"+String.valueOf(ad.getId()), Toast.LENGTH_SHORT).show();
        }
    }
}
