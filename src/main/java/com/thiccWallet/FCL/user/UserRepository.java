package com.thiccWallet.FCL.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findAppUserByUsername(String username);
    Optional<User> findAppUserByEmail(String email);

    @Query("from User au where au.username = :username and au.password = :password")
    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
