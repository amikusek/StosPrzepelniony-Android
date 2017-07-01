package pl.sggw.stosprzepelniony.viper.categories;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

import java.util.List;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.viper.categories.adapter.item.RootCategory;

interface CategoriesContract {


    interface View extends MvpView {
        Observable<Integer> getListItemClicks();
        void setMessagesList(List<RootCategory> items);
        void showLoading();
        void showError(Throwable throwable);
    }

    interface Interactor extends ViperRxInteractor {
        Observable<List<RootCategory>> getCategories();
    }

    interface Routing extends ViperRxRouting<Activity> {
        void replaceByAdFragmentWithCategoryFilter(int categoryId);
    }
}
