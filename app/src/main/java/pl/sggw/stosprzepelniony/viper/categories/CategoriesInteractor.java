package pl.sggw.stosprzepelniony.viper.categories;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import pl.sggw.stosprzepelniony.data.entity.Category;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;
import pl.sggw.stosprzepelniony.viper.categories.adapter.item.RootCategory;

class CategoriesInteractor
        extends BaseRxInteractor
        implements CategoriesContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<List<RootCategory>> getCategories() {

        List<Category> children = new ArrayList<>();
        children.add(new Category(134, "Children 1"));
        children.add(new Category(243, "Children 2"));
        children.add(new Category(3434, "Children 3"));
        children.add(new Category(4342, "Children 4"));

        return retrofitFactory
                .getCategoriesAPI()
                .getCategories()
                .flatMap(categories ->
                        Observable
                                .fromIterable(categories)
                                .map(category -> new RootCategory(category.getCategoryName(), children))
                                .toList().toObservable())
                .subscribeOn(Schedulers.io());
    }

}
