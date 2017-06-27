package pl.sggw.stosprzepelniony.data.network.util;

import io.reactivex.Completable;
import retrofit2.http.GET;

public interface StosPrzepelnionyUsersAPI {

    @GET("users/isloggedin")
    Completable checkUserSessionStatus();
}
