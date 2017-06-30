package pl.sggw.stosprzepelniony.viper.categories;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

public class CategoriesPresenter
        extends BaseRxPresenter<CategoriesContract.View,
        CategoriesContract.Interactor,
        CategoriesContract.Routing>
        implements ViperPresenter<CategoriesContract.View> {

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
