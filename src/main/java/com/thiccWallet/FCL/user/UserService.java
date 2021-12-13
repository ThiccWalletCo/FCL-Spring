package com.thiccWallet.FCL.user;

import com.thiccWallet.FCL.common.exception.DuplicateCredentialsException;
import com.thiccWallet.FCL.common.exception.InvalidRequestException;
import com.thiccWallet.FCL.login.dtos.request.LoginRequest;
import com.thiccWallet.FCL.user.dtos.requests.UserCreationRequest;
import com.thiccWallet.FCL.user.dtos.requests.UserEditRequest;
import com.thiccWallet.FCL.user.dtos.responses.UserCreatedResponse;
import com.thiccWallet.FCL.user.dtos.responses.UserResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

        if (!isUserCreationRequestValid(userCreationRequest)) {
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

    @Transactional
    public Optional<User> loginUser(LoginRequest loginRequest) {
        if (!isLoginRequestValid(loginRequest)) {
            throw new InvalidRequestException("Invalid credentials entered");
        }
        return userRepo.findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @Transactional
    public void deleteUser(User user) {
        userRepo.deleteById(user.getId());
    }

    @Transactional
    public User editUser(UserEditRequest userEditRequest, User user) {
        if(isFieldValid(userEditRequest.getUsername())) {
            user.setUsername(userEditRequest.getUsername());
        }
        if(isFieldValid(userEditRequest.getEmail())) {
            user.setEmail(userEditRequest.getEmail());
        }
        if(isFieldValid(userEditRequest.getPassword())) {
            user.setPassword(userEditRequest.getPassword());
        }

        return userRepo.save(user);
    }

    private boolean isUserCreationRequestValid(UserCreationRequest newUser) {
        if (!isFieldValid(newUser.getUsername())) return false;
        if (!isFieldValid(newUser.getEmail())) return false;
        return isFieldValid(newUser.getPassword());
    }

    private boolean isLoginRequestValid(LoginRequest loginRequest) {
        if (!isFieldValid(loginRequest.getUsername())) return false;
        return isFieldValid(loginRequest.getPassword());
    }

    private boolean isFieldValid(String field) {
        return field != null && !field.trim().equals("");
    }

}
