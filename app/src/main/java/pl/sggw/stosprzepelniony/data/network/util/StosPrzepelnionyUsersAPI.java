package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.entity.User;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StosPrzepelnionyUsersAPI {

    @GET("users/isloggedin")
    Completable checkUserSessionStatus();

    @GET("users/{userId}/info")
    Observable<User> getUserById(@Path("userId") int userId);
}
