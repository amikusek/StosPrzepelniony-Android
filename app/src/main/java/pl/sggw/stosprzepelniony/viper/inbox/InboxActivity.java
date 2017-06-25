package pl.sggw.stosprzepelniony.viper.inbox;


import android.support.annotation.NonNull;

import pl.sggw.stosprzepelniony.R;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class InboxActivity
        extends ViperButterKnifePassiveActivity
        <InboxContract.View>
        implements InboxContract.View {


    @NonNull
    @Override
    public ViperPresenter<InboxContract.View> createPresenter() {
        return new InboxPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_inbox;
    }
}
