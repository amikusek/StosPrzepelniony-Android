package pl.sggw.stosprzepelniony.viper.ad_details;


import android.support.annotation.NonNull;

import pl.sggw.stosprzepelniony.R;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class AdDetailsActivity
        extends ViperButterKnifePassiveActivity
        <AdDetailsContract.View>
        implements AdDetailsContract.View {


    @NonNull
    @Override
    public ViperPresenter<AdDetailsContract.View> createPresenter() {
        return new AdDetailsPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ad_details;
    }
}
