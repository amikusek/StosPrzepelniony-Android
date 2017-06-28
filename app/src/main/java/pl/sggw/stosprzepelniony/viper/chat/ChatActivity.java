package pl.sggw.stosprzepelniony.viper.chat;


import android.support.annotation.NonNull;

import pl.sggw.stosprzepelniony.R;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class ChatActivity
        extends ViperButterKnifePassiveActivity
        <ChatContract.View>
        implements ChatContract.View {


    @NonNull
    @Override
    public ViperPresenter<ChatContract.View> createPresenter() {
        return new ChatPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }
}
