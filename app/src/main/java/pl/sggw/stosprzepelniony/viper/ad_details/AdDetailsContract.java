package pl.sggw.stosprzepelniony.viper.ad_details;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.MessageBundle;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

interface AdDetailsContract {


    interface View extends MvpView {
        String AD_ID_EXTRA = "AD_ID_EXTRA";
        Observable<Object> getBackButtonClicks();
        PublishSubject<MessageBundle> getSendButtonClicks();
        void showContent(Ad ad);
        void showError(Throwable throwable);
        void showLoading();
    }

    interface Interactor extends ViperRxInteractor {
        Observable<Ad> getAdById(int adId);
    }

    interface Routing extends ViperRxRouting<Activity> {
        void closeScreen();
        void startChatScreen(MessageBundle messageBundle);
    }
}
