package com.thiccWallet.FCL.data.coin.dtos.responses;

import com.thiccWallet.FCL.data.coin.Coin;

public class CoinResponse {

    private String currPair;
    private double amount;

    public CoinResponse() {
    }

    public CoinResponse(String currPair, double amount) {
        this.currPair = currPair;
        this.amount = amount;
    }

    public CoinResponse(Coin coin) {
        this.currPair = coin.getCoinId().getCurrPair();
        this.amount = coin.getAmount();
    }

    public String getCurrPair() {
        return currPair;
    }

    public void setCurrPair(String currPair) {
        this.currPair = currPair;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CoinResponse{" +
                "currPair='" + currPair + '\'' +
                ", amount=" + amount +
                '}';
    }
}
