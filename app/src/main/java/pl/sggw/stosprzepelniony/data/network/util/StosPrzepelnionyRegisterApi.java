package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.entity.RegisterBundle;

import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StosPrzepelnionyRegisterApi {

    @POST("users/register")
    Completable performRegistration(@Body RegisterBundle registerBundle);
}