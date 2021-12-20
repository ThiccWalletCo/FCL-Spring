package com.thiccWallet.FCL.endpoints.wallets.dtos.responses;

import com.thiccWallet.FCL.data.coin.dtos.responses.CoinResponse;

import java.util.List;

public class WalletResponse {

    private String username;
    private double totalWalletValue;
    private double usdBalance;
    private List<CoinResponse> coins;

    public WalletResponse() {
    }

    public WalletResponse(String username, double totalWalletValue, double usdBalance, List<CoinResponse> coins) {
        this.username = username;
        this.totalWalletValue = totalWalletValue;
        this.usdBalance = usdBalance;
        this.coins = coins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalWalletValue() {
        return totalWalletValue;
    }

    public void setTotalWalletValue(double totalWalletValue) {
        this.totalWalletValue = totalWalletValue;
    }

    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }

    public List<CoinResponse> getCoins() {
        return coins;
    }

    public void setCoins(List<CoinResponse> coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "WalletResponse{" +
                "username='" + username + '\'' +
                ", totalWalletValue=" + totalWalletValue +
                ", usdBalance=" + usdBalance +
                ", coins=" + coins +
                '}';
    }
}
