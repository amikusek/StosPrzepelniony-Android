package pl.sggw.stosprzepelniony.di;

import android.content.Context;

import pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage.PersistentCookieStorage;
import pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage.impl.shared_preferences.SharedPreferencesPersistentCookieStorage;

public class DIProvider {

    private static DIProvider instance;
    private Context appContext;

    private DIProvider() {
    }

    public static DIProvider getInstance() {
        if (instance == null) {
            instance = new DIProvider();
        }
        return instance;
    }

    public void init(Context context) {
        this.appContext = context;
    }

    public PersistentCookieStorage getPersistentCookieStorage() {
        assertNonNullContext();
        return new SharedPreferencesPersistentCookieStorage(appContext);
    }

    private void assertNonNullContext() {
        if (appContext == null) {
            throw new IllegalStateException("You have to init DIProvider with application context first");
        }
    }
}