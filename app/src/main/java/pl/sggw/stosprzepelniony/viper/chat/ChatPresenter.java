package pl.sggw.stosprzepelniony.viper.chat;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class ChatPresenter
        extends BaseRxPresenter
        <ChatContract.View,
                ChatContract.Interactor,
                ChatContract.Routing>
        implements ViperPresenter<ChatContract.View> {

    @NonNull
    @Override
    public ChatContract.Routing createRouting() {
        return new ChatRouting();
    }

    @NonNull
    @Override
    public ChatContract.Interactor createInteractor() {
        return new ChatInteractor();
    }
}
