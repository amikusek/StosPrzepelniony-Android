package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.entity.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface StosPrzepelnionyCategoriesAPI {

    @GET("categories/list")
    Observable<List<Category>> getCategories();
}
