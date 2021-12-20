package com.thiccWallet.FCL.endpoints.LeaderboardController.dtos.responses;

public class LeaderboardResponse {

    private String username;
    private double balance;

    public LeaderboardResponse() {
    }

    public LeaderboardResponse(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "LeaderboardResponse{" +
                "username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }

}
