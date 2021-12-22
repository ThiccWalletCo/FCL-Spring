package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.endpoints.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends CrudRepository<League, String> {
    public Optional<League> findLeagueByLeagueName(String leagueName);

    @Query("from League l join Wallet w on l.id = w.league join User u on w.owner = u.id where u.id = :userId")
    List<League> findLeaguesByUserId(String userId);

}
