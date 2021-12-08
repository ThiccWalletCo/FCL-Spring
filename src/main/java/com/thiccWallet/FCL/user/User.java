package com.thiccWallet.FCL.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK (email <> '')")
    private String email;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK (username <> '')")
    private String username;

    @Column(nullable = false, unique = false, columnDefinition = "VARCHAR CHECK (password <> '')")
    private String password;

    @Column(nullable = true, unique = false, columnDefinition = "VARCHAR CHECK (email <> '')")
    private LocalDateTime dateCreated;

    public User(String id, String email, String username, String password, LocalDateTime dateCreated) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(dateCreated, user.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, dateCreated);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
