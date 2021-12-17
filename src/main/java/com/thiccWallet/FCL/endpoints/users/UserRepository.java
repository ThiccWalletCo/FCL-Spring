package com.thiccWallet.FCL.endpoints.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    //@Query("from User u where u.username = :username and u.password = :password")
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("from User u join Wallet w on u.id = w.owner join League l on w.league = l.id where l.id = :leagueId")
    List<User> findUsersByLeagueId(String leagueId);

}
