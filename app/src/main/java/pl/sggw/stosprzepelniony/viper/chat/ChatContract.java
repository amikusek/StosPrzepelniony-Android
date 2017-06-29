package pl.sggw.stosprzepelniony.viper.chat;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.ChatMessageBundle;
import pl.sggw.stosprzepelniony.data.entity.Message;

interface ChatContract {


    interface View extends MvpView {

        Observable<Object> getBackButtonClicks();
        Observable<ChatMessageBundle> getSendButtonClicks();
        void showLoading();
        void showError(Throwable throwable, boolean showErrorView);
        void showContent(List<Message> messages);
        void showEmptyState();
        void updateView();
    }

    interface Interactor extends ViperRxInteractor {

        Observable<List<Message>> getMessagesFromUserById(int senderId);
        Completable sendMessage(ChatMessageBundle bundle);
    }

    interface Routing extends ViperRxRouting<Activity> {

        void closeScreen();
    }
}
