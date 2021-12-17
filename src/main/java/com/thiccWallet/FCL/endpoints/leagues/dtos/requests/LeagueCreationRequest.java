package com.thiccWallet.FCL.endpoints.leagues.dtos.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class LeagueCreationRequest {

    @NotBlank
    private String name;

    @Min(1)
    private double initialBalance;

    public LeagueCreationRequest() {
    }

    public LeagueCreationRequest(String name, double initialBalance) {
        this.name = name;
        this.initialBalance = initialBalance;
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
}
