package com.thiccWallet.FCL.session.login;

import com.thiccWallet.FCL.common.exception.DuplicateLoginAttemptException;
import com.thiccWallet.FCL.common.exception.NoSuchElementException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.session.login.dtos.request.LoginRequest;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.UserService;
import com.thiccWallet.FCL.session.login.dtos.responses.PrincipalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    //@ResponseStatus(HttpStatus.OK)//returns a principal object for front end
    @PostMapping(consumes = "application/json")
    public PrincipalResponse loginUser(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest req) {
        HttpSession session = req.getSession(false);//false means it does not return new session if one doesn't exist

        if (session != null) {
            throw new DuplicateLoginAttemptException("User is already logged in!");
        }

        Optional<User> foundUser = userService.loginUser(loginRequest);

        if (foundUser.isPresent()){
            User authorizedUser = foundUser.get();

            session = req.getSession();

            session.setAttribute("authorizedUser", authorizedUser);
            return new PrincipalResponse(authorizedUser);

        } else {
            throw new NoSuchElementException("Could not locate user given provided credentials");
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
