package com.thiccWallet.FCL.coin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="coins")
public class Coin {
    @Id
    @Column(name = "wallet_id")
    private String walletId;
    @Id
    @Column(name = "coin_pair", nullable = false, unique = false, columnDefinition = "VARCHAR CHECK(coin_pair <> '')")
    private String coinPair;
    @Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
    private double amount;//price of given coin in double format

    public Coin(String walletId, String coinName, String coinPair, double amount) {
        this.walletId = walletId;
        this.coinPair = coinPair;
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

    public String getCoinPair() {
        return coinPair;
    }

    public void setCoinPair(String coinPair) {
        this.coinPair = coinPair;
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
                ", coinPair='" + coinPair + '\'' +
                ", amount=" + amount +
                '}';
    }

}
