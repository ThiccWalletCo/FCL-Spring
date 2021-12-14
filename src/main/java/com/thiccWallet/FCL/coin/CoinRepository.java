package com.thiccWallet.FCL.coin;

import com.thiccWallet.FCL.coin.dtos.responses.CoinResponse;
import com.thiccWallet.FCL.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinRepository extends CrudRepository<Coin, String> {

    @Query("from Coin c where c.walletId = :wallet_id")
    Optional<User> findCoinsByWalletId(Coin WalletId);

}
