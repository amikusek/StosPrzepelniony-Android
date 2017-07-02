package pl.sggw.stosprzepelniony.viper.ad_details;

import android.app.Activity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.data.entity.MessageBundle;
import pl.sggw.stosprzepelniony.viper.chat.ChatActivity;

class AdDetailsRouting
        extends BaseRxRouting<Activity>
        implements AdDetailsContract.Routing {

    @Override
    public void closeScreen() {
        if (isContextAttached()) getRelatedContext().finish();
    }

    @Override
    public void startChatScreen(MessageBundle messageBundle) {
        if (isContextAttached())
            ChatActivity.start(
                    getRelatedContext(),
                    messageBundle.getSenderUserId(),
                    messageBundle.getAdId(),
                    messageBundle.getSenderName());
    }
}
