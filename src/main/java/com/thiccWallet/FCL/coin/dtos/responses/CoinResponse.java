package com.thiccWallet.FCL.coin.dtos.responses;

import com.thiccWallet.FCL.coin.Coin;
import com.thiccWallet.FCL.user.User;

import java.time.LocalDateTime;

public class CoinResponse {

    private String walletId;
    private String currencyPair;
    private double amount;

    public CoinResponse(Coin coin) {
        this.walletId = coin.getWalletId();
        this.currencyPair = coin.getCurrPair();
        this.amount = coin.getAmount();
    }

    public CoinResponse() {
        super();
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
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
                "walletId='" + walletId + '\'' +
                ", currencyPair='" + currencyPair + '\'' +
                ", amount=" + amount +
                '}';
    }
}
