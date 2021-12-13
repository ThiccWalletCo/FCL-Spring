package com.thiccWallet.FCL.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    @Query("from User u where u.username = :username and u.password = :password")
    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
