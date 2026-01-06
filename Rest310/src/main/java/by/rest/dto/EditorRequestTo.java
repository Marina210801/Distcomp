package by.rest.dto;

import jakarta.validation.constraints.NotBlank;

public class EditorRequestTo {
    @NotBlank private String login;
    @NotBlank private String password;
    @NotBlank private String firstname;
    @NotBlank private String lastname;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
}
