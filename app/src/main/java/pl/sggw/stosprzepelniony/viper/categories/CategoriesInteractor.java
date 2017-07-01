package pl.sggw.stosprzepelniony.viper.categories;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.Category;
import pl.sggw.stosprzepelniony.viper.categories.adapter.item.RootCategory;

class CategoriesInteractor
        extends BaseRxInteractor
        implements CategoriesContract.Interactor {

    @Override
    public Observable<List<RootCategory>> getCategories() {
        List<RootCategory> categories = new ArrayList<>();
        List<Category> children = new ArrayList<>();
        children.add(new Category(134, "Children 1"));
        children.add(new Category(243, "Children 2"));
        children.add(new Category(3434, "Children 3"));
        children.add(new Category(4342, "Children 4"));
        categories.add(new RootCategory("Category one", children));
        categories.add(new RootCategory("Category two", children));
        categories.add(new RootCategory("Category three", children));

        return Observable.just(categories);
    }

}
