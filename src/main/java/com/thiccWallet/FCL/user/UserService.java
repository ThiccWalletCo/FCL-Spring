package com.thiccWallet.FCL.user;

import com.thiccWallet.FCL.user.dtos.responses.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = ((Collection<User>) userRepo.findAll())
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

        if (users.isEmpty()) {
            // TODO - Create custom exceptions
            // throw new ResourceNotFoundException();
        }

        return users;
    }
}
