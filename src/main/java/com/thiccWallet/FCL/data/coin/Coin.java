package com.thiccWallet.FCL.data.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(CoinId.class)
@Table(name = "coins")
public class Coin {
    @Id
    private String walletId;

    @Id
    @JsonProperty("id")
    private String currPair;

    @Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
    private double amount;//price of given coin in double format

    public Coin(String walletId, String currPair, double amount) {
        this.walletId = walletId;
        this.currPair = currPair;
        this.amount = amount;
    }

    public Coin() {}

    public Coin(Coin coin) {
        this.walletId = coin.getWalletId();
        this.currPair = coin.getCurrPair();
        this.amount = coin.getAmount();
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
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
        return "Coin{" +
                "walletId='" + walletId + '\'' +
                ", coinPair='" + currPair + '\'' +
                ", amount=" + amount +
                '}';
    }

}