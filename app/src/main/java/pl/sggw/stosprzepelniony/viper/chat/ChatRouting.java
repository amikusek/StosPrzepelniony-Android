package pl.sggw.stosprzepelniony.viper.chat;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

class ChatRouting
        extends BaseRxRouting<Activity>
        implements ChatContract.Routing {

    @Override
    public void closeScreen() {
        if (isContextAttached()) getRelatedContext().finish();
    }

}
