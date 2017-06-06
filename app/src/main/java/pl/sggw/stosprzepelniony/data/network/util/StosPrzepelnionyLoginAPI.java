package pl.sggw.stosprzepelniony.data.network.util;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.result.LoginResult;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StosPrzepelnionyLoginAPI {

    @FormUrlEncoded
    @POST("login")
    Observable<LoginResult> performLogin(
            @Field("username") String username,
            @Field("password") String password);
}
