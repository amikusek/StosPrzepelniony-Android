package pl.sggw.stosprzepelniony.data.network.util;

import io.reactivex.Completable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StosPrzepelnionyRegisterApi {

    @FormUrlEncoded
    @POST("register")
    Completable performRegistration(
            @Field("email") String email,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("password") String password);
}