package com.thiccWallet.FCL.data.coin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends CrudRepository<Coin, CoinId> {
    //@Query("from Coin where wallet_id = :walletId")
    @Query("from Coin where coinId.walletId = :walletId")
    List<Coin> findCoinsByWalletId(String walletId);
}
