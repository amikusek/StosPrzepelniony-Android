package pl.sggw.stosprzepelniony.viper.new_advertisement;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import io.reactivex.Completable;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;

interface NewAdvertisementContract {


    interface View extends MvpView {
        Observable<Object> getDismissButtonClicks();
        Observable<NewAdvertisementBundle> getAddButtonClicks();
        void showLoading();
        void showError(Throwable throwable);
        void showConfirmationInfo();
    }

    interface Interactor extends ViperRxInteractor {
        Completable performAddAdvertisement(NewAdvertisementBundle newAdvertisementBundle);
    }

    interface Routing extends ViperRxRouting<Activity> {
        void closeScreen();
    }
}
