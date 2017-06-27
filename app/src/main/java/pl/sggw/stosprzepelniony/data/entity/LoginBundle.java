package pl.sggw.stosprzepelniony.data.entity;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class LoginBundle {
    private String email;
    private String password;

    public LoginBundle(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginBundle withHashedPassword() {
        password = Hashing.sha256().hashString(password, Charset.forName("UTF-8")).toString();
        return this;
    }
}
