package com.thiccWallet.FCL.login;

import com.thiccWallet.FCL.common.exception.DuplicateLoginAttemptException;
import com.thiccWallet.FCL.common.exception.NoSuchUserException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.login.dtos.request.LoginRequest;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(consumes = "application/json")
    public void loginUser(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null) {
            throw new DuplicateLoginAttemptException("User is already logged in!");
        }

        Optional<User> foundUser = userService.loginUser(loginRequest);

        if (foundUser.isPresent()){
            User authorizedUser = foundUser.get();

            session = req.getSession();

            session.setAttribute("authorizedUser", authorizedUser);

        } else {
            throw new NoSuchUserException("Could not locate user given provided credentials");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logoutUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("User is not logged in");
        }

        session.invalidate();
    }

}
