package com.thiccWallet.FCL.endpoints.wallets;

import com.thiccWallet.FCL.data.coin.Coin;
import com.thiccWallet.FCL.endpoints.leagues.League;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.wallets.dtos.responses.JoinSuccessResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    WalletRepository walletRepo;

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

    public JoinSuccessResponse createWallet(League league, User authUser) {
        Wallet newWallet = new Wallet(authUser, league, league.getInitialBalance());

        newWallet.setWalletID(UUID.randomUUID().toString());

        return new JoinSuccessResponse(walletRepo.save(newWallet));
    }
}