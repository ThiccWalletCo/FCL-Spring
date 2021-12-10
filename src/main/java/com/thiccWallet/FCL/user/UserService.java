package com.thiccWallet.FCL.user;

import com.thiccWallet.FCL.common.exception.DuplicateCredentialsException;
import com.thiccWallet.FCL.common.exception.InvalidRequestException;
import com.thiccWallet.FCL.user.dtos.requests.UserCreationRequest;
import com.thiccWallet.FCL.user.dtos.responses.UserCreatedResponse;
import com.thiccWallet.FCL.user.dtos.responses.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
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

    @Transactional
    public UserCreatedResponse createNewUser(UserCreationRequest userCreationRequest) {

        if (!isUserCreationRequestvalid(userCreationRequest)) {
            throw new InvalidRequestException("Invalid Credentials entered: " + userCreationRequest);
        }

        boolean usernameTaken = userRepo.findUserByUsername(userCreationRequest.getUsername()).isPresent();
        boolean emailTaken = userRepo.findUserByEmail(userCreationRequest.getEmail()).isPresent();

        if (usernameTaken || emailTaken) {
            String msg = "The following values are already in use:";
            if (usernameTaken) msg += "\n\t- username: " + userCreationRequest.getUsername();
            if (emailTaken) msg += "\n\t- email: " + userCreationRequest.getEmail();
            throw new DuplicateCredentialsException(msg);
        }

        User newUser = new User(userCreationRequest);
        newUser.setId(UUID.randomUUID().toString());

        userRepo.save(newUser);

        return new UserCreatedResponse(newUser);
    }

    private boolean isUserCreationRequestvalid(UserCreationRequest newUser) {
        if (newUser.getUsername() == null || newUser.getUsername().trim().equals("")) return false;
        if (newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        return newUser.getPassword() != null && !newUser.getPassword().trim().equals("");
    }

}
