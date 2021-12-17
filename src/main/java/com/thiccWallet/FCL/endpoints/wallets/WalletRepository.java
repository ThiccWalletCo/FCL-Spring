package com.thiccWallet.FCL.endpoints.wallets;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, String> {

    Optional<Wallet> findWalletByLeagueIdAndUserId(String leagueId, String userId);
}
