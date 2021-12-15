package com.thiccWallet.FCL.wallet;

import com.thiccWallet.FCL.coin.Coin;

import java.util.List;

public class WalletService {

    //uses annotations in the rest controller
    //if user_id is provided, returns wallets associated with user
    //if league is provided, returns wallets associated with league
    public List<Wallet> getWallets(){
        return null;
    }

    //returns a usd total to be posted to leaderboard
    public double getTotal(){
        //TODO: implement me
        return -1;
    }

    //returns a list of owned coins in a given wallet.
    public List<Coin> getCoinValues()
    {
        //TODO: implement me
        return null;
    }
}