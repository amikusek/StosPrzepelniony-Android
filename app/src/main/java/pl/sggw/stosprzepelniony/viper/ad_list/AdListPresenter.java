package pl.sggw.stosprzepelniony.viper.ad_list;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class AdListPresenter
        extends BaseRxPresenter
        <AdListContract.View,
                AdListContract.Interactor,
                AdListContract.Routing>
        implements ViperPresenter<AdListContract.View> {

    @NonNull
    @Override
    public AdListContract.Routing createRouting() {
        return new AdListRouting();
    }

    @NonNull
    @Override
    public AdListContract.Interactor createInteractor() {
        return new AdListInteractor();
    }
}
