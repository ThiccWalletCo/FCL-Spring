package com.thiccWallet.FCL.wallet;

import com.thiccWallet.FCL.league.League;
import com.thiccWallet.FCL.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    private String walletID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @Column(name = "initial_bal")
    private double initialBalance;

    @Column(name = "wallet_bal")
    private double walletBalance;

    @Column(name = "cash_bal")
    private double cashBalance;

    //------------------------------------------------

    public Wallet() {

    }

    public Wallet(User owner, League league, double initialBalance, double walletBalance, double cashBalance) {
        this.owner = owner;
        this.league = league;
        this.initialBalance = initialBalance;
        this.walletBalance = walletBalance;
        this.cashBalance = cashBalance;
    }

    public Wallet(String walletID, User owner, League league, double initialBalance, double walletBalance, double cashBalance) {
        this.walletID = walletID;
        this.owner = owner;
        this.league = league;
        this.initialBalance = initialBalance;
        this.walletBalance = walletBalance;
        this.cashBalance = cashBalance;
    }

    //-------------------------------------------------------

    public String getWalletID() {
        return walletID;
    }

    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    //--------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.initialBalance, initialBalance) == 0 && Double.compare(wallet.walletBalance, walletBalance) == 0 && Double.compare(wallet.cashBalance, cashBalance) == 0 && Objects.equals(walletID, wallet.walletID) && Objects.equals(owner, wallet.owner) && Objects.equals(league, wallet.league);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletID, owner, league, initialBalance, walletBalance, cashBalance);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletID='" + walletID + '\'' +
                ", owner=" + owner +
                ", league=" + league +
                ", initialBalance=" + initialBalance +
                ", walletBalance=" + walletBalance +
                ", cashBalance=" + cashBalance +
                '}';
    }
}
