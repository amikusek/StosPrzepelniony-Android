package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;

import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StosPrzepelnionyAddAdvertisementAPI {

    @POST("ads/add")
    Completable performAddAdvertisement(@Body NewAdvertisementBundle newAdvertisementBundle);
}
