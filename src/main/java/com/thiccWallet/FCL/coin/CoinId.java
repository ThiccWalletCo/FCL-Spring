package com.thiccWallet.FCL.coin;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CoinId implements Serializable {

    private String currPair;
    private String walletId;

    public CoinId(){};

    public CoinId(String currencyPair, String walletId) {
        this.currPair = currencyPair;//same currency pair
        this.walletId = walletId; //same wallet id
        //combination of curr_pair and wallet_id is always unique
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinId coinId = (CoinId) o;
        return Objects.equals(currPair, coinId.currPair) && Objects.equals(walletId, coinId.walletId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currPair, walletId);
    }
}
