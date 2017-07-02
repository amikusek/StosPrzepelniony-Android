package pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage.impl.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage.PersistentStorage;

public class SharedPreferencesPersistentStorage implements PersistentStorage {

    private static final String SHARED_PREFS_LOCATION = "StackOverflow";
    private static final String COOKIES_STORE_LOCATION = "Cookies";
    private static final String SESSION_TOKEN_STORE_LOCATION = "SessionToken";
    private static final String AUTO_SIGN_IN_LOCATION = "AutoSignIn";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesPersistentStorage(Context context) {
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

    @Override
    public void saveSessionToken(String token) {
        sharedPreferences
                .edit()
                .putString(SESSION_TOKEN_STORE_LOCATION, token)
                .apply();
    }

    @Override
    public String getSessionToken() {
        return sharedPreferences.getString(SESSION_TOKEN_STORE_LOCATION, "");
    }

    @Override
    public void clearSessionToken() {
        sharedPreferences
                .edit()
                .remove(SESSION_TOKEN_STORE_LOCATION)
                .apply();
    }

    @Override
    public void setAutoSignInEnabled(boolean enabled) {
        sharedPreferences
                .edit()
                .putBoolean(AUTO_SIGN_IN_LOCATION, enabled)
                .apply();
    }

    @Override
    public boolean isAutoSignInEnabled() {
        return sharedPreferences.getBoolean(AUTO_SIGN_IN_LOCATION, true);
    }

}


