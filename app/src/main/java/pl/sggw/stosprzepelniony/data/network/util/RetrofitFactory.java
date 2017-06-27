package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.network.util.interceptors.RestoreCookiesFromPersistentStorageInterceptor;
import pl.sggw.stosprzepelniony.data.network.util.interceptors.SaveCookiesToPersistentStorageInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "https://api.stackexchange.com";
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

    public StosPrzepelnionyAddAdvertisementAPI getAddAdvertisementAPI() {
        return retrofit.create(StosPrzepelnionyAddAdvertisementAPI.class);
    }

    public StosPrzepelnionyUsersAPI getUsersAPI() {
        return retrofit.create(StosPrzepelnionyUsersAPI.class);
    }
}