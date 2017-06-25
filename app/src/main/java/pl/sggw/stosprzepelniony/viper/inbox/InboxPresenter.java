package pl.sggw.stosprzepelniony.viper.inbox;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class InboxPresenter
        extends BaseRxPresenter
        <InboxContract.View,
                InboxContract.Interactor,
                InboxContract.Routing>
        implements ViperPresenter<InboxContract.View> {

    @NonNull
    @Override
    public InboxContract.Routing createRouting() {
        return new InboxRouting();
    }

    @NonNull
    @Override
    public InboxContract.Interactor createInteractor() {
        return new InboxInteractor();
    }
}
