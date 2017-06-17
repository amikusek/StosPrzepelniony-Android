package pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage.impl.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage.PersistentCookieStorage;

import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesPersistentCookieStorage implements PersistentCookieStorage {

    private static final String SHARED_PREFS_LOCATION = "StackOverflow";
    private static final String COOKIES_STORE_LOCATION = "Cookies";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesPersistentCookieStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_LOCATION, Context.MODE_PRIVATE);
    }

    @Override
    public void saveCookies(Set<String> cookies) {
        sharedPreferences
                .edit()
                .putStringSet(COOKIES_STORE_LOCATION, cookies)
                .apply();
    }

    @Override
    public Set<String> getCookies() {
        return sharedPreferences.getStringSet(COOKIES_STORE_LOCATION, new HashSet<String>());
    }

    @Override
    public void removeCookies() {
        sharedPreferences
                .edit()
                .remove(COOKIES_STORE_LOCATION)
                .apply();
    }
}


