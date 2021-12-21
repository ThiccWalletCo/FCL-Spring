package com.thiccWallet.FCL.endpoints.wallets;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, String> {

    Optional<Wallet> findWalletByLeagueIdAndOwnerId(String leagueId, String userId);

    List<Wallet> getWalletsByLeagueId(String id);
}
