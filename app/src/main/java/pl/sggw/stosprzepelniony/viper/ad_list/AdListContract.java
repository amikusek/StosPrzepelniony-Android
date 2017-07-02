package pl.sggw.stosprzepelniony.viper.ad_list;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.AdsFilter;

import java.util.List;

import io.reactivex.Observable;


interface AdListContract {

    interface View extends MvpView {

        Observable<Ad> getAdClicks();
        AdsFilter getAdsFilter();
        Observable<Object> adsFilterChangedEvents();
        Observable<Object> loadAdsEvents();
        Observable<Object> refreshes();
        Observable<Object> getFabEvents();
        void setAdsItems(List<Ad> ads);
        void showError(Throwable throwable);
        void showLoading();
    }

    interface Interactor extends ViperRxInteractor {

        Observable<List<Ad>> getAdsWithFilter(AdsFilter adsFilter);
    }

    interface Routing extends ViperRxRouting<Activity> {
        void startNewAdActivity();
        void startAdDetailsActivity(int adId);
    }
}
