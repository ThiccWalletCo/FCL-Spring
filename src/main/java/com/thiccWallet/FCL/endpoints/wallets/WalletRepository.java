package com.thiccWallet.FCL.endpoints.wallets;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, String> {

    Optional<Wallet> findWalletByLeagueIdAndOwnerId(String leagueId, String userId);

    List<Wallet> getWalletsByLeagueId(String id);

    //    @Query("from User u join Wallet w on u.id = w.owner join League l on w.league = l.id where l.id = :leagueId")
    @Query("FROM Wallet w join User u on u.id = w.owner join League l on w.league = l.id where l.leagueName = :leagueName and u.username = :username")
    Wallet getWalletByUsernameAndLeagueName(String username, String leagueName);

}
