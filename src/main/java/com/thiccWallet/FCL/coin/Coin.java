package com.thiccWallet.FCL.coin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coins")
public class Coin {
    @Id
    @Column(name = "wallet_id", nullable = false, unique = false, columnDefinition = "VARCHAR CHECK(coin_pair <> '')")
    private String walletId;
    @Column(name = "curr_pair", nullable = false, unique = false, columnDefinition = "VARCHAR CHECK(coin_pair <> '')")
    private String currPair;
    @Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
    private double amount;//price of given coin in double format

    public Coin(String walletId, String coinName, String currPair, double amount) {
        this.walletId = walletId;
        this.currPair = currPair;
        this.amount = amount;
    }

    public Coin() {//for jackson
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
