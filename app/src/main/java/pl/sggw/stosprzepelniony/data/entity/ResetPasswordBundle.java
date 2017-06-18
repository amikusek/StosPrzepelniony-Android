package pl.sggw.stosprzepelniony.data.entity;

public class ResetPasswordBundle {

    String email;

    public ResetPasswordBundle(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
