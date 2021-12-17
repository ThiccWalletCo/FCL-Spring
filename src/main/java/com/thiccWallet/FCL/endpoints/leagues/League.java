package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.endpoints.leagues.dtos.requests.LeagueCreationRequest;
import com.thiccWallet.FCL.endpoints.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "leagues")
public class League {

    @Id
    @Column(name = "league_id")
    private String id;

    // If something is broken check here
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User owner;

    @Column(name = "league_name", unique = true, columnDefinition = "VARCHAR CHECK (league_name <> '')")
    private String leagueName;

    @Column(name = "initial_bal", columnDefinition = "NUMERIC CHECK (INITIAL_BAL > 0)")
    private double initialBalance;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @OneToMany
    List<User> joinedUsers;

    public League() {

    }

    public League(User owner, String leagueName, double initialBalance) {
        this.owner = owner;
        this.leagueName = leagueName;
        this.initialBalance = initialBalance;

        this.id = UUID.randomUUID().toString();
        this.dateCreated = LocalDateTime.now();
    }

    public League(String leagueID, User owner, String leagueName, double initialBalance) {
        this.id = leagueID;
        this.owner = owner;
        this.leagueName = leagueName;
        this.initialBalance = initialBalance;

        this.dateCreated = LocalDateTime.now();
    }

    public League(LeagueCreationRequest creationRequest, User user) {
        this.owner = user;
        this.leagueName = creationRequest.getName();
        this.initialBalance = creationRequest.getInitialBalance();

        this.id = UUID.randomUUID().toString();
        this.dateCreated = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
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
        League league = (League) o;
        return Double.compare(league.initialBalance, initialBalance) == 0 && Objects.equals(id, league.id) && Objects.equals(owner, league.owner) && Objects.equals(leagueName, league.leagueName) && Objects.equals(dateCreated, league.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, leagueName, initialBalance, dateCreated);
    }

    @Override
    public String toString() {
        return "League{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                ", leagueName='" + leagueName + '\'' +
                ", initialBalance=" + initialBalance +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
