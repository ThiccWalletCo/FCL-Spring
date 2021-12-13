package com.thiccWallet.FCL.league;

import com.thiccWallet.FCL.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class League {

    @Id
    @Column(name = "league_id")
    private String leagueID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User leagueOwner;

    @Column(name = "league_name")
    private String leagueName;

    @Column
    private String password;

    @Column(name = "initial_bal")
    private double initialBalance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "league_users",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "league_id") }
    )
    List<User> joinedUsers;

    public League() {

    }

    public League(User leagueOwner, String leagueName, String password, double initialBalance) {
        this.leagueOwner = leagueOwner;
        this.leagueName = leagueName;
        this.password = password;
        this.initialBalance = initialBalance;
    }

    public League(String leagueID, User leagueOwner, String leagueName, String password, double initialBalance) {
        this.leagueID = leagueID;
        this.leagueOwner = leagueOwner;
        this.leagueName = leagueName;
        this.password = password;
        this.initialBalance = initialBalance;
    }

    public League(String leagueID, User leagueOwner, String leagueName, String password, double initialBalance, List<User> joinedUsers) {
        this.leagueID = leagueID;
        this.leagueOwner = leagueOwner;
        this.leagueName = leagueName;
        this.password = password;
        this.initialBalance = initialBalance;
        this.joinedUsers = joinedUsers;
    }

    public String getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
    }

    public User getLeagueOwner() {
        return leagueOwner;
    }

    public void setLeagueOwner(User leagueOwner) {
        this.leagueOwner = leagueOwner;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public List<User> getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(List<User> joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return Double.compare(league.initialBalance, initialBalance) == 0 && Objects.equals(leagueID, league.leagueID) && Objects.equals(leagueOwner, league.leagueOwner) && Objects.equals(leagueName, league.leagueName) && Objects.equals(password, league.password) && Objects.equals(joinedUsers, league.joinedUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueID, leagueOwner, leagueName, password, initialBalance, joinedUsers);
    }

    @Override
    public String toString() {
        return "League{" +
                "leagueID='" + leagueID + '\'' +
                ", leagueOwner=" + leagueOwner +
                ", leagueName='" + leagueName + '\'' +
                ", password='" + password + '\'' +
                ", initialBalance=" + initialBalance +
                ", joinedUsers=" + joinedUsers +
                '}';
    }
}
