package pl.sggw.stosprzepelniony.viper.ad_details;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AdDetailsPresenter
        extends BaseRxPresenter
        <AdDetailsContract.View,
                AdDetailsContract.Interactor,
                AdDetailsContract.Routing>
        implements ViperPresenter<AdDetailsContract.View> {

    AdDetailsPresenter(Bundle args) {
        super(args);
    }

    @Override
    public void attachView(AdDetailsContract.View view) {
        super.attachView(view);

        addSubscription(
                Single
                        .just(Irrelevant.EVENT)
                        .filter(event -> isViewAttached())
                        .flatMapObservable(event -> getInteractor().getAdById(getArgs().getInt(AdDetailsContract.View.AD_ID_EXTRA)))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ad -> getView().showContent(ad)
                                , error -> getView().showError(error)));

        addSubscription(
                getView()
                        .getBackButtonClicks()
                        .filter(event -> isViewAttached())
                        .subscribe(event -> getRouting().closeScreen()));
    }

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
