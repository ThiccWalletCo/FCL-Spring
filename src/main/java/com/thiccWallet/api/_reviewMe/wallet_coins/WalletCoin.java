package com.thiccWallet.api._reviewMe.wallet_coins;

import com.thiccWallet.api._reviewMe.coin.Coin;
import com.thiccWallet.api.models.wallet.Wallet;

import javax.persistence.*;

@Entity
@Table(name = "wallet_coins")
public class WalletCoin {

    @Id
    @Column(name = "walletcoin_id")
    public String walletCoinId;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    public Wallet walletId;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    public Coin coinId;

    @Column(nullable = false, unique = false)
    public double amount;

    public String getWalletCoinId() {
        return walletCoinId;
    }

    public void setWalletCoinId(String walletCoinId) {
        this.walletCoinId = walletCoinId;
    }

    public Wallet getWalletId() {
        return walletId;
    }

    public void setWalletId(Wallet walletId) {
        this.walletId = walletId;
    }

    public Coin getCoinId() {
        return coinId;
    }

    public void setCoinId(Coin coinId) {
        this.coinId = coinId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "WalletCoin{" +
                "walletCoinId='" + walletCoinId + '\'' +
                ", walletId='" + walletId + '\'' +
                ", coinId='" + coinId + '\'' +
                ", amount=" + amount +
                '}';
    }

}
