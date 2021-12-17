package com.thiccWallet.FCL.data.coin.dtos.requests;

public class CoinSellRequest {
    private String currPair;
    private double amount;

    public CoinSellRequest(String currPair, double amount) {
        this.currPair = currPair;
        this.amount = amount;
    }

    public CoinSellRequest() {
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
        return "CoinSellRequest{" +
                "currPair='" + currPair + '\'' +
                ", amount=" + amount +
                '}';
    }
}
