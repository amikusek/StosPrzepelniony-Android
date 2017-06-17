package pl.sggw.stosprzepelniony.data.network.util.persistent_cookie_storage;

import java.util.Set;

public interface PersistentCookieStorage {

    void saveCookies(Set<String> cookies);

    Set<String> getCookies();

    void removeCookies();
}