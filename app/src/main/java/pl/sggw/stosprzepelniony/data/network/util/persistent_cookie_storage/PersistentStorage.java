package pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage;

import java.util.Set;

public interface PersistentStorage {

    void saveCookies(Set<String> cookies);
    Set<String> getCookies();
    void removeCookies();
    void saveSessionToken(String token);
    String getSessionToken();
}