package com.thiccWallet.api.data.user;

import com.thiccWallet.api.util.common.exception.DuplicateCredentialsException;
import com.thiccWallet.api.util.common.exception.InvalidRequestException;
import com.thiccWallet.api.util.login.dtos.request.LoginRequest;
import com.thiccWallet.api.data.user.dtos.requests.UserCreationRequest;
import com.thiccWallet.api.data.user.dtos.requests.UserEditRequest;
import com.thiccWallet.api.data.user.dtos.responses.UserCreatedResponse;
import com.thiccWallet.api.data.user.dtos.responses.UserResponse;
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
        System.out.println("Attempting to getAllUsers in UserService");
        List<UserResponse> users = ((Collection<User>) userRepo.findAll())
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

        return users;
    }

    @Transactional
    public UserCreatedResponse createNewUser(UserCreationRequest userCreationRequest) {

        if (!isUserCreationRequestValid(userCreationRequest)) {
            throw new InvalidRequestException("Invalid Credentials entered: " + userCreationRequest);
        }

        checkCredentialsNotTaken(userCreationRequest.getUsername(), userCreationRequest.getEmail());

        User newUser = new User(userCreationRequest);
        newUser.setId(UUID.randomUUID().toString());

        userRepo.save(newUser);

        return new UserCreatedResponse(newUser);
    }

    @Transactional(readOnly = true)
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

        checkCredentialsNotTaken(userEditRequest.getUsername(), userEditRequest.getEmail());

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

    @Transactional(readOnly = true)
    public boolean isUsernameAvailable(String username) {
        return !userRepo.findUserByUsername(username).isPresent();
    }

    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email) {
        return !userRepo.findUserByEmail(email).isPresent();
    }

    @Transactional(readOnly = true)
    private void checkCredentialsNotTaken(String username, String email) {
        boolean usernameTaken = userRepo.findUserByUsername(username).isPresent();
        boolean emailTaken = userRepo.findUserByEmail(email).isPresent();

        if (usernameTaken || emailTaken) {
            String msg = "The following values are already in use:";
            if (usernameTaken) msg += "\n\t- username: " + username;
            if (emailTaken) msg += "\n\t- email: " + email;
            throw new DuplicateCredentialsException(msg);
        }
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
