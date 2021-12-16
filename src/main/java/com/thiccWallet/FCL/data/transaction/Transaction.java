package com.thiccWallet.FCL.data.transaction;

import com.thiccWallet.FCL.endpoints.wallets.Wallet;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    private String id;

    @Column(name = "currency_pair", nullable = false)
    private String currencyPair;

    @Column(name = "usd_change", nullable = false)
    private Double usdChange;

    @Column(name = "coin_change", nullable = false)
    private Double coinChange;

    @Column(name = "wallet_value_after_transaction", nullable = false)
    private Double walletValueAfterTransaction;

    @JoinColumn(nullable = false)
    private Wallet wallet;

    public Transaction(){
        super();
    }

    public Transaction(String id, String currencyPair, Double usdChange, Double coinChange, Double walletValueAfterTransaction, Wallet wallet) {
        this.id = id;
        this.currencyPair = currencyPair;
        this.usdChange = usdChange;
        this.coinChange = coinChange;
        this.walletValueAfterTransaction = walletValueAfterTransaction;
        this.wallet = wallet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Double getUsdChange() {
        return usdChange;
    }

    public void setUsdChange(Double usdChange) {
        this.usdChange = usdChange;
    }

    public Double getCoinChange() {
        return coinChange;
    }

    public void setCoinChange(Double coinChange) {
        this.coinChange = coinChange;
    }

    public Double getWalletValueAfterTransaction() {
        return walletValueAfterTransaction;
    }

    public void setWalletValueAfterTransaction(Double walletValueAfterTransaction) {
        this.walletValueAfterTransaction = walletValueAfterTransaction;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(currencyPair, that.currencyPair) && Objects.equals(usdChange, that.usdChange) && Objects.equals(coinChange, that.coinChange) && Objects.equals(walletValueAfterTransaction, that.walletValueAfterTransaction) && Objects.equals(wallet, that.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyPair, usdChange, coinChange, walletValueAfterTransaction, wallet);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", currencyPair='" + currencyPair + '\'' +
                ", usdChange=" + usdChange +
                ", coinChange=" + coinChange +
                ", walletValueAfterTransaction=" + walletValueAfterTransaction +
                ", wallet='" + wallet + '\'' +
                '}';
    }
}