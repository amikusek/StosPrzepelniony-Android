package pl.sggw.stosprzepelniony.viper.inbox;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import java.util.List;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.MessageBundle;
import pl.sggw.stosprzepelniony.data.entity.MessageListItem;

interface InboxContract {


    interface View extends MvpView {
        void showLoading();
        void showError(Throwable throwable);
        void setMessagesList(List<MessageListItem> items);
        void showEmptyState();
        Observable<MessageBundle> getListItemClicks();
    }

    interface Interactor extends ViperRxInteractor {
        Observable<List<MessageListItem>> getMessages();
    }

    interface Routing extends ViperRxRouting<Activity> {
        void startChatActivity(MessageBundle bundle);
    }
}
