package com.thiccWallet.FCL.data.coin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends CrudRepository<Coin, String> {

    @Query("SELECT coin.currPair from Coin coin")
    List<Coin> getAllCoins();

    Coin findCoinByWalletIdAndCurrPair(String walletId, String Curr_Pair);

    List<Coin> findCoinsByWalletId(String walletId);
}
