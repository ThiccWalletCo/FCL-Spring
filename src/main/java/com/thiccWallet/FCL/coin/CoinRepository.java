package com.thiccWallet.FCL.coin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends CrudRepository {
    List<Coin> findCoinsByUserId(String userId);
}
