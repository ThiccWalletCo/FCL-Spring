package com.thiccWallet.FCL.endpoints.leagues;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueRepository extends CrudRepository<League, String> {
    public Optional<League> findLeagueByLeagueName(String leagueName);

}
