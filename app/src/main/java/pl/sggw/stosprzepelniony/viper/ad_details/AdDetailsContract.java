package pl.sggw.stosprzepelniony.viper.ad_details;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import pl.sggw.stosprzepelniony.data.entity.Ad;

import io.reactivex.Observable;

interface AdDetailsContract {


    interface View extends MvpView {
        String AD_ID_EXTRA = "AD_ID_EXTRA";
        Observable<Object> getBackButtonClicks();
        void showContent(Ad ad);
        void showError(Throwable throwable);
        void showLoading();
    }

    interface Interactor extends ViperRxInteractor {
        Observable<Ad> getAdById(int adId);
    }

    interface Routing extends ViperRxRouting<Activity> {
        void closeScreen();
    }
}
