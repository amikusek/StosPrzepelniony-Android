package pl.sggw.stosprzepelniony.viper.new_advertisement;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;
import pl.sggw.stosprzepelniony.data.entity.SingleCategory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

interface NewAdvertisementContract {


    interface View extends MvpView {

        int NO_CATEGORY = -997;
        Observable<Object> getCategoriesClicks();
        Observable<Object> getDismissButtonClicks();
        Observable<NewAdvertisementBundle> getAddButtonClicks();
        void showLoading();
        void showError(Throwable throwable);
        void showConfirmationInfo();
        void showCategoryChoosingDialog();
        void setCategories(List<SingleCategory> categories);
    }

    interface Interactor extends ViperRxInteractor {
        Completable performAddAdvertisement(NewAdvertisementBundle newAdvertisementBundle);
        Observable<List<SingleCategory>> getCategories();
    }

    interface Routing extends ViperRxRouting<Activity> {
        void closeScreen();
    }
}
