package pl.sggw.stosprzepelniony.viper.new_advertisement;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class NewAdvertisementPresenter
        extends BaseRxPresenter
        <NewAdvertisementContract.View,
                NewAdvertisementContract.Interactor,
                NewAdvertisementContract.Routing>
        implements ViperPresenter<NewAdvertisementContract.View> {

    @NonNull
    @Override
    public NewAdvertisementContract.Routing createRouting() {
        return new NewAdvertisementRouting();
    }

    @NonNull
    @Override
    public NewAdvertisementContract.Interactor createInteractor() {
        return new NewAdvertisementInteractor();
    }
}
