package com.thiccWallet.FCL.data.coin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends CrudRepository<Coin, CoinId> {

//    @Query("SELECT coin.currPair from Coin coin")
//    List<Coin> getAllCoins();

    //List<Coin> findCoinsByWalletId(String walletId);
}
