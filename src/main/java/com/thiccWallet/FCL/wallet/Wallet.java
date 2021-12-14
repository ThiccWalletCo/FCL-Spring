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

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User owner;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League leagueId;

    @Column(name = "initial_bal")
    private double initialBalance;

//    don't need
//    @Column(name = "wallet_bal")
//    private double walletBalance;

    @Column(name = "usd_bal")
    private double usdBalance;

    public String getWalletID() {
        return walletID;
    }

    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    public League getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(League leagueId) {
        this.leagueId = leagueId;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletID='" + walletID + '\'' +
                ", leagueId=" + leagueId +
                ", initialBalance=" + initialBalance +
                ", usdBalance=" + usdBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.initialBalance, initialBalance) == 0 && Double.compare(wallet.usdBalance, usdBalance) == 0 && Objects.equals(walletID, wallet.walletID) && Objects.equals(leagueId, wallet.leagueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletID, leagueId, initialBalance, usdBalance);
    }
}
