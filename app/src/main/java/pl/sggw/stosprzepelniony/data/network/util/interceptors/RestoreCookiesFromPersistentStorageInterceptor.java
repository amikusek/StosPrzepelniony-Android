package pl.sggw.stosprzepelniony.data.network.util.interceptors;

import pl.sggw.stosprzepelniony.di.DIProvider;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RestoreCookiesFromPersistentStorageInterceptor implements Interceptor {

    private String COOKIE_HEADER_FIELD_NAME = "Cookie";
    private String AUTHENTICATION_HEADER_FIELD_NAME = "Authorization";

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for (String cookie : DIProvider.getInstance().getPersistentStorage().getCookies()) {
            builder.addHeader(COOKIE_HEADER_FIELD_NAME, cookie);
        }
        builder.addHeader(
                AUTHENTICATION_HEADER_FIELD_NAME,
                "Basic " + DIProvider.getInstance().getPersistentStorage().getSessionToken());
        return chain.proceed(builder.build());
    }
}
