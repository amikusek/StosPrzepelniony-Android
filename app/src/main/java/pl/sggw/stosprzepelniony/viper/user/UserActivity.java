package pl.sggw.stosprzepelniony.viper.user;


import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.ViperAiPassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;

public class UserActivity
        extends ViperAiPassiveActivity
        <UserContract.View>
        implements UserContract.View {


    @NonNull
    @Override
    public ViperPresenter<UserContract.View> createPresenter() {
        return new UserPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }
}
