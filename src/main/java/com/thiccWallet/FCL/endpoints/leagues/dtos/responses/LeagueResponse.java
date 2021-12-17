package com.thiccWallet.FCL.endpoints.leagues.dtos.responses;

import com.thiccWallet.FCL.endpoints.leagues.League;

import java.time.LocalDateTime;

public class LeagueResponse {

    private String leagueName;
    private double initialBal;
    private String creatorName;
    private LocalDateTime dateCreated;

    public LeagueResponse(){

    }

    public LeagueResponse(League league) {
        this.leagueName = league.getLeagueName();
        this.initialBal = league.getInitialBalance();
        this.creatorName = league.getOwner().getUsername();
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
                ", creatorName=" + creatorName +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
