package com.thiccWallet.FCL.user;

import com.thiccWallet.FCL.user.dtos.responses.UserResponse;

import org.springframework.web.bind.annotation.*;

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

}
