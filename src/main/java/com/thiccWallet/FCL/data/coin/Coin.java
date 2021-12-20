package com.thiccWallet.FCL.data.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coins")
public class Coin {

    @EmbeddedId
    private CoinId coinId;
    
    @Column(nullable = false, columnDefinition = "NUMERIC CHECK (amount > 0)")
    private double amount;//price of given coin in double format

    public Coin() {}

    public Coin(String walletId, String currPair, double amount) {
        this.coinId = new CoinId(currPair, walletId);
        this.amount = amount;
    }

    public Coin(Coin coin) {
        this.coinId = new CoinId(coin.getCoinId().getCurrPair(), coin.getCoinId().getWalletId());
        this.amount = coin.getAmount();
    }

    public CoinId getCoinId() {
        return coinId;
    }

    public void setCoinId(CoinId coinId) {
        this.coinId = coinId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return Double.compare(coin.amount, amount) == 0 && Objects.equals(coinId, coin.coinId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinId, amount);
    }

    @Override
    public String toString() {
        return "Coin{" +
                "coinId=" + coinId +
                ", amount=" + amount +
                '}';
    }
}
