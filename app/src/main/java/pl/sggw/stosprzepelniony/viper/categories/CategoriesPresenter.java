package pl.sggw.stosprzepelniony.viper.categories;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class CategoriesPresenter
        extends BaseRxPresenter<CategoriesContract.View,
        CategoriesContract.Interactor,
        CategoriesContract.Routing>
        implements ViperPresenter<CategoriesContract.View> {

    @Override
    public void attachView(CategoriesContract.View view) {
        super.attachView(view);

        addSubscription(
                Single
                        .just(Irrelevant.EVENT)
                        .filter(event -> isViewAttached())
                        .flatMapObservable(event -> getInteractor().getCategories())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(categories -> getView().setMessagesList(categories),
                                error -> getView().showError(error)));
        addSubscription(
                getView()
                        .getListItemClicks()
                        .filter(event -> isViewAttached())
                        .subscribe(categoryId ->
                                getRouting().replaceByAdFragmentWithCategoryFilter(categoryId)));
    }

    @NonNull
    @Override
    public CategoriesContract.Routing createRouting() {
        return new CategoriesRouting();
    }

    @NonNull
    @Override
    public CategoriesContract.Interactor createInteractor() {
        return new CategoriesInteractor();
    }
}
