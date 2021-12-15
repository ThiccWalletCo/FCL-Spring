package com.thiccWallet.api.models.user.dtos.responses;

import com.thiccWallet.api.models.user.User;

import java.time.LocalDateTime;

public class UserCreatedResponse {

    private String userID;
    private String username;
    private String email;
    private LocalDateTime dateCreated;

    public UserCreatedResponse() {

    }

    public UserCreatedResponse(String userID, String username, String email, LocalDateTime dateCreated) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.dateCreated = dateCreated;
    }

    public UserCreatedResponse(User user) {
        this.userID = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.dateCreated = user.getDateCreated();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "UserCreatedResponse{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
