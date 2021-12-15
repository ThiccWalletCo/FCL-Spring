package com.thiccWallet.FCL.endpoints.users.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserCreationRequest {

    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UserCreationRequest() {

    }

    public UserCreationRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCreationRequest{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
