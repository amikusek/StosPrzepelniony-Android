package pl.sggw.stosprzepelniony.data.network.util;

import okhttp3.OkHttpClient;
import pl.sggw.stosprzepelniony.data.network.util.interceptors.RestoreCookiesFromPersistentStorageInterceptor;
import pl.sggw.stosprzepelniony.data.network.util.interceptors.SaveCookiesToPersistentStorageInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

//    private static final String BASE_URL = "https://api.stackexchange.com";
    private static final String BASE_URL = "http://192.168.0.13:3000";
    private Retrofit retrofit;

    public RetrofitFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createCookiesPersistentHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient createCookiesPersistentHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new RestoreCookiesFromPersistentStorageInterceptor())
                .addInterceptor(new SaveCookiesToPersistentStorageInterceptor())
                .build();
    }

    public StosPrzepelnionyLoginAPI getLoginApi() {
        return retrofit.create(StosPrzepelnionyLoginAPI.class);
    }

    public StosPrzepelnionyRegisterApi getRegisterApi() {
        return retrofit.create(StosPrzepelnionyRegisterApi.class);
    }

    public StosPrzepelnionyResetPasswordAPI getResetPasswordAPI() {
        return retrofit.create(StosPrzepelnionyResetPasswordAPI.class);
    }

    public StosPrzepelnionyAdvertisementAPI getAdvertisementAPI() {
        return retrofit.create(StosPrzepelnionyAdvertisementAPI.class);
    }

    public StosPrzepelnionyUsersAPI getUsersAPI() {
        return retrofit.create(StosPrzepelnionyUsersAPI.class);
    }

    public StosPrzepelnionyCategoriesAPI getCategoriesAPI() {
        return retrofit.create(StosPrzepelnionyCategoriesAPI.class);
    }

    public StosPrzepelnionyMessagesAPI getMessagesAPI() {
        return retrofit.create(StosPrzepelnionyMessagesAPI.class);
    }
}