package pl.sggw.stosprzepelniony.data.entity;

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
}
