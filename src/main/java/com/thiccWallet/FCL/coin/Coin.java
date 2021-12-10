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
    @Column(name = "coin_id")
    private String coinId;
    @Column(nullable = false, unique = false, columnDefinition = "VARCHAR CHECK (coinId <> '')")
    private String coinName;
    @Column(nullable = false, unique = false, columnDefinition = "VARCHAR CHECK(coinPair <> ''")
    private String coinPair;
    @Column(nullable = false, unique = false,columnDefinition = "NUMERIC CHECK(ticker <> ''")
    private double ticker;//price of given coin in double format

    public Coin(String coinId, String coinName, String coinPair, double ticker) {
        this.coinId = coinId;
        this.coinName = coinName;
        this.coinPair = coinPair;
        this.ticker = ticker;
    }
    public Coin(){//for jackson
    }



    public String getCoinId() {
        return coinId;
    }


    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinPair() {
        return coinPair;
    }

    public void setCoinPair(String coinPair) {
        this.coinPair = coinPair;
    }

    public double getTicker() {
        return ticker;
    }

    public void setTicker(double ticker) {
        this.ticker = ticker;
    }

    public List<Coin> getCoins(){
        return null;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "coinId='" + coinId + '\'' +
                ", coinName='" + coinName + '\'' +
                ", coinPair='" + coinPair + '\'' +
                ", ticker=" + ticker +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinId, coinName, coinPair, ticker);
    }
}
