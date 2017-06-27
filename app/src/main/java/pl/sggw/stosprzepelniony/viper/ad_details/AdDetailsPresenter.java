package pl.sggw.stosprzepelniony.viper.ad_details;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class AdDetailsPresenter
        extends BaseRxPresenter
        <AdDetailsContract.View,
                AdDetailsContract.Interactor,
                AdDetailsContract.Routing>
        implements ViperPresenter<AdDetailsContract.View> {

    @NonNull
    @Override
    public AdDetailsContract.Routing createRouting() {
        return new AdDetailsRouting();
    }

    @NonNull
    @Override
    public AdDetailsContract.Interactor createInteractor() {
        return new AdDetailsInteractor();
    }
}
