package pl.sggw.stosprzepelniony.data.network.util;

import io.reactivex.Completable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StosPrzepelnionyResetPasswordAPI {
    @FormUrlEncoded
    @POST("users/resetpassword")
    Completable performPasswordRestoring(@Field("email") String email);
}
