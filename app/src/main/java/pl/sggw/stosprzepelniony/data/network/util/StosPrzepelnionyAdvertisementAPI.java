package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StosPrzepelnionyAdvertisementAPI {

    @POST("ads/add")
    Completable performAddAdvertisement(@Body NewAdvertisementBundle newAdvertisementBundle);

    @GET("ads/{adId}/info")
    Observable<Ad> getAdById(@Path("adId") int adId);

    @GET("ads/list")
    Observable<List<Ad>> getAdsForUserId(@Query("userId") int adId);

    @GET("ads/list")
    Observable<List<Ad>> getAds();
}
