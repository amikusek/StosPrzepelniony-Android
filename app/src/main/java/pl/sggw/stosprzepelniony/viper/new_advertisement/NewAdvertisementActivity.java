package pl.sggw.stosprzepelniony.viper.new_advertisement;


import android.support.annotation.NonNull;

import pl.sggw.stosprzepelniony.R;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class NewAdvertisementActivity
        extends ViperButterKnifePassiveActivity
        <NewAdvertisementContract.View>
        implements NewAdvertisementContract.View {


    @NonNull
    @Override
    public ViperPresenter<NewAdvertisementContract.View> createPresenter() {
        return new NewAdvertisementPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_advertisement;
    }
}
