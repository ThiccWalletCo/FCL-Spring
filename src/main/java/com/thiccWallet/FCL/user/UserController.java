package com.thiccWallet.FCL.user;

import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.user.dtos.requests.UserCreationRequest;
import com.thiccWallet.FCL.user.dtos.responses.UserCreatedResponse;
import com.thiccWallet.FCL.user.dtos.responses.UserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    /** TODO
     * Make  a UserService
     * Make a UserDAO
     * Make DTOs
     */

    private UserService userService;

    public UserController(UserService userService) {
        // Do we need to do anything in the Constructor?
        this.userService = userService; // Does Spring handle this in a nicer way?
    }

    // All users
    @GetMapping(produces = "application/json")
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    // Single user
    // @GetMapping(value = "/{uuid}")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserCreatedResponse createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        return userService.createNewUser(userCreationRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("No User currently logged in: could not delete");
        }

        User authorizedUser = (User)session.getAttribute("authorizedUser");
        userService.deleteUser(authorizedUser);

        session.invalidate();

    }


}
