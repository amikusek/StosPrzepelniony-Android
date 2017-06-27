package pl.sggw.stosprzepelniony.data.entity;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class RegisterBundle {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirmation;

    public RegisterBundle(String email, String firstName, String lastName, String password, String passwordConfirmation) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public RegisterBundle withHashedPassword() {
        password = Hashing.sha256().hashString(password, Charset.forName("UTF-8")).toString();
        return this;
    }
}
