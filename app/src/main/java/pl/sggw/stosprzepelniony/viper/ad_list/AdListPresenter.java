package pl.sggw.stosprzepelniony.viper.ad_list;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withRetryErrorLogic;

public class AdListPresenter
        extends BaseRxPresenter
        <AdListContract.View,
                AdListContract.Interactor,
                AdListContract.Routing>
        implements ViperPresenter<AdListContract.View> {

    private PublishSubject<Object> loadAdsSubject = PublishSubject.create();

    @Override
    public void attachView(AdListContract.View view) {
        super.attachView(view);

        addSubscription(
                loadAdsSubject
                        .filter(event -> isViewAttached())
                        .doOnNext(event -> getView().showLoading())
                        .map(emptyEvent -> getView().getAdsFilter())
                        .flatMap(getInteractor()::getAdsWithFilter)
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withRetryErrorLogic(getView()::showError))
                        .subscribe(view::setAdsItems));
        addSubscription(
                getView()
                        .adsFilterChangedEvents()
                        .doOnNext(loadAdsSubject::onNext)
                        .subscribe());
        addSubscription(
                getView()
                        .refreshes()
                        .doOnNext(loadAdsSubject::onNext)
                        .subscribe());

        loadAdsSubject.onNext(Irrelevant.EVENT);
    }

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
