package com.thiccWallet.api.models.user;

import com.thiccWallet.api.common.exception.NotLoggedInException;
import com.thiccWallet.api.models.user.dtos.requests.UserCreationRequest;
import com.thiccWallet.api.models.user.dtos.requests.UserEditRequest;
import com.thiccWallet.api.models.user.dtos.responses.UserCreatedResponse;
import com.thiccWallet.api.models.user.dtos.responses.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/** TODO
 * Make  a UserService
 * Make a UserDAO
 * Make DTOs
 */

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        // Do we need to do anything in the Constructor?
        this.userService = userService; // Does Spring handle this in a nicer way?
    }
    public UserController(){super();}

    // All users
    // TODO - This should be handled by a DTO
    @GetMapping(value = "/all", produces = "application/json")
    public List<UserResponse> allUsers() {
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = "application/json")
    public void editUser(@RequestBody UserEditRequest userEditRequest, HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if(session == null) {
            throw new NotLoggedInException("No User currently logged in: could not edit");
        }

        User authorizedUser = (User)session.getAttribute("authorizedUser");

        userService.editUser(userEditRequest, authorizedUser);
    }

    @GetMapping("/username")
    public ResponseEntity<Void> checkUsernameAvailability(@RequestParam String username) {
        return userService.isUsernameAvailable(username) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/email")
    public ResponseEntity<Void> checkEmailAvailability(@RequestParam String email) {
        return userService.isEmailAvailable(email) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // Sanity check
    @GetMapping(value = "/test")
    public String testResponse() {
        return "If you are reading this, something went right!";
    }

}
