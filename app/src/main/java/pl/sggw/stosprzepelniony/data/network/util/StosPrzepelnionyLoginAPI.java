package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.data.result.LoginResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StosPrzepelnionyLoginAPI {

    @POST("users/login")
    Observable<LoginResult> performLogin(@Body LoginBundle loginBundle);
}
