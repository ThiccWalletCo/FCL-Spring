package com.thiccWallet.FCL.endpoints.leagues;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends CrudRepository<League, String> {


}
