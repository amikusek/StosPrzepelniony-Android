package pl.sggw.stosprzepelniony.data.network.util.interceptors;

import pl.sggw.stosprzepelniony.di.DIProvider;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Response;

public class SaveCookiesToPersistentStorageInterceptor implements Interceptor {

    private static final String COOKIE_HEADER_FIELD_NAME = "Set-Cookie";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers(COOKIE_HEADER_FIELD_NAME).isEmpty()) { // `headers()` will never return null
            Set<String> cookies = new HashSet<>(originalResponse.headers(COOKIE_HEADER_FIELD_NAME));
            DIProvider.getInstance().getPersistentCookieStorage().saveCookies(cookies);
        }
        return originalResponse;
    }
}
