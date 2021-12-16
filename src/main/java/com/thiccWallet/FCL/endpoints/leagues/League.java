package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.endpoints.users.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "leagues")
public class League {

    @Id
    @Column(name = "id")
    private String id;

    // If something is broken check here
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "league_name", unique = true, columnDefinition = "VARCHAR CHECK (league_name <> '')")
    private String leagueName;

    // QOL goal protect certain leagues with password
//    @Column
//    private String password;

    @Column(name = "initial_bal", columnDefinition = "NUMERIC CHECK (INITIAL_BAL > 0)")
    private double initialBalance;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "league_users",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "league_id") }
//    )
//    List<User> joinedUsers;

    public League() {

    }

    public League(User owner, String leagueName, double initialBalance) {
        this.owner = owner;
        this.leagueName = leagueName;
        this.initialBalance = initialBalance;
    }

    public League(String leagueID, User owner, String leagueName, double initialBalance) {
        this.id = leagueID;
        this.owner = owner;
        this.leagueName = leagueName;
        this.initialBalance = initialBalance;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return Double.compare(league.initialBalance, initialBalance) == 0 && Objects.equals(id, league.id) && Objects.equals(owner, league.owner) && Objects.equals(leagueName, league.leagueName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, leagueName, initialBalance);
    }

    @Override
    public String toString() {
        return "League{" +
                "leagueID='" + id + '\'' +
                ", leagueOwner=" + owner +
                ", leagueName='" + leagueName + '\'' +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
