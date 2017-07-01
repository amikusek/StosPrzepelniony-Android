package pl.sggw.stosprzepelniony.viper.ad_list;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.data.util.CategoriesCacheStorage;
import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withObservableRetryErrorLogic;

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
//                        .observeOn(Schedulers.computation())
                        .flatMap(ads -> Observable.fromIterable(ads).map(ad -> {
                            ad.withCategory(CategoriesCacheStorage.getInstance().getCategoryById(ad.getCategoryId()));
                            System.out.println(ad.getCategory());
                            return ad;
                        }).toList().toObservable())
/*                        .map(ad -> {
                                    ad.withCategory(CategoriesCacheStorage.getInstance().getCategoryById(ad.getId()));
                                    System.out.println(ad.getCategory());
                                    return ad;
                                }
                        )
                        .toList()
                        .toObservable()*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withObservableRetryErrorLogic(getView()::showError))
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
        addSubscription(
                getView()
                        .getFabEvents()
                        .filter(event -> isViewAttached())
                        .subscribe(event -> getRouting().startNewAdActivity()));

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
