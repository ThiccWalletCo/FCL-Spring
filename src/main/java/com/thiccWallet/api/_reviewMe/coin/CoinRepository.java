package com.thiccWallet.api._reviewMe.coin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends CrudRepository<Coin, String> {
    List<Coin> findCoinsByCoinId(String coinId);
}