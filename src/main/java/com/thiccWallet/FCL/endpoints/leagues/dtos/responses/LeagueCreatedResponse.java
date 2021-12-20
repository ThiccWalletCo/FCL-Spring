package com.thiccWallet.FCL.endpoints.leagues.dtos.responses;

import com.thiccWallet.FCL.endpoints.leagues.League;

import java.time.LocalDateTime;

public class LeagueCreatedResponse {

    private String name;
    private double initialBalance;
    private String creatorName;
    private LocalDateTime dateCreated;

    public LeagueCreatedResponse() {
    }

    public LeagueCreatedResponse(String name, double initialBalance, String creatorName, LocalDateTime dateCreated) {
        this.name = name;
        this.initialBalance = initialBalance;
        this.creatorName = creatorName;
        this.dateCreated = dateCreated;
    }

    public LeagueCreatedResponse(League league) {
        this.name = league.getLeagueName();
        this.initialBalance = league.getInitialBalance();
        this.creatorName = league.getOwner().getUsername();
        this.dateCreated = league.getDateCreated();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
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
        return "LeagueCreatedResponse{" +
                "name='" + name + '\'' +
                ", initialBalance=" + initialBalance +
                ", creatorName='" + creatorName + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
