package com.thiccWallet.FCL.endpoints.wallets;

import com.thiccWallet.FCL.data.coin.Coin;
import com.thiccWallet.FCL.endpoints.leagues.League;
import com.thiccWallet.FCL.endpoints.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @Column(name = "wallet_id", columnDefinition = "VARCHAR")
    private String walletID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @Column(name = "cash_bal", columnDefinition = "NUMERIC CHECK (INITIAL_BAL >= 0)")
    private double cashBalance;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @OneToMany
    private List<Coin> walletCoins;

    //------------------------------------------------

    public Wallet() {

    }

    public Wallet(User owner, League league, double cashBalance) {
        this.owner = owner;
        this.league = league;
        this.cashBalance = cashBalance;

        this.dateCreated = LocalDateTime.now();
    }

    public Wallet(String walletID, User owner, League league, double cashBalance, LocalDateTime dateCreated) {
        this.walletID = walletID;
        this.owner = owner;
        this.league = league;
        this.cashBalance = cashBalance;
        this.dateCreated = dateCreated;
    }

    public Wallet(String walletID, User owner, League league, double cashBalance, LocalDateTime dateCreated, List<Coin> walletCoins) {
        this.walletID = walletID;
        this.owner = owner;
        this.league = league;
        this.cashBalance = cashBalance;
        this.dateCreated = dateCreated;
        this.walletCoins = walletCoins;
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

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Coin> getWalletCoins() {
        return walletCoins;
    }

    public void setWalletCoins(List<Coin> walletCoins) {
        this.walletCoins = walletCoins;
    }

    //--------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.cashBalance, cashBalance) == 0 && Objects.equals(walletID, wallet.walletID) && Objects.equals(owner, wallet.owner) && Objects.equals(league, wallet.league) && Objects.equals(dateCreated, wallet.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletID, owner, league, cashBalance, dateCreated);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletID='" + walletID + '\'' +
                ", owner=" + owner +
                ", league=" + league +
                ", cashBalance=" + cashBalance +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
