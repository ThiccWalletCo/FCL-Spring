package com.thiccWallet.FCL.endpoints.leagues.dtos;

import com.thiccWallet.FCL.endpoints.leagues.League;
import com.thiccWallet.FCL.endpoints.users.User;

import java.time.LocalDateTime;

public class LeagueResponse {

    private String leagueName;
    private double initialBal;
    private User creator;
    private LocalDateTime dateCreated;

    public LeagueResponse(){

    }

    public LeagueResponse(League league) {
        this.leagueName = league.getLeagueName();
        this.initialBal = league.getInitialBalance();
        this.creator = league.getOwner();
        this.dateCreated = league.getDateCreated();
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public double getInitialBal() {
        return initialBal;
    }

    public void setInitialBal(double initialBal) {
        this.initialBal = initialBal;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "LeagueResponse{" +
                "leagueName='" + leagueName + '\'' +
                ", initialBal=" + initialBal +
                ", creator=" + creator +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
