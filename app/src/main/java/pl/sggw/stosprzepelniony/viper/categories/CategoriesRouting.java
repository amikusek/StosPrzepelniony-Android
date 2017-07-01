package pl.sggw.stosprzepelniony.viper.categories;

import android.app.Activity;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

class CategoriesRouting
        extends BaseRxRouting<Activity>
        implements CategoriesContract.Routing {

    @Override
    public void replaceByAdFragmentWithCategoryFilter(int categoryId) {
        Toast.makeText(getRelatedContext(), "Replace by Ad Fragment with Ads from category" + categoryId, Toast.LENGTH_SHORT).show();
    }
}
