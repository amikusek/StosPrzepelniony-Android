package pl.sggw.stosprzepelniony.data.network.util;

import io.reactivex.Completable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StosPrzepelnionyAddAdvertisementAPI {
    @FormUrlEncoded
    @POST("ads/add")
    Completable performAddAdvertisement(
            @Field("categoryId") int categoryId,
            @Field("subject") String subject,
            @Field("content") String description,
            @Field("costHour") double costHour,
            @Field("costTotal") double costTotal);
}
