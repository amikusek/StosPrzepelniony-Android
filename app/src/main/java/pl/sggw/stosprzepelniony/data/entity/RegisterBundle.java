package pl.sggw.stosprzepelniony.data.entity;

public class RegisterBundle {
    String email;
    String firstName;
    String lastName;
    String password;
    String passwordConfirmation;

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
}
