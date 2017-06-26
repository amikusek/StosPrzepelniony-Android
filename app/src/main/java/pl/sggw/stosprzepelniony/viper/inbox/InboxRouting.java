package pl.sggw.stosprzepelniony.viper.inbox;

import android.app.Activity;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.data.entity.MessageBundle;

class InboxRouting
        extends BaseRxRouting<Activity>
        implements InboxContract.Routing {

    @Override
    public void startChatActivity(MessageBundle bundle) {
        Toast.makeText(getRelatedContext(), "Routing: start ChatActivity here", Toast.LENGTH_SHORT).show();
    }
}
