package com.thiccWallet.FCL.session.login;

import com.thiccWallet.FCL.common.exception.DuplicateLoginAttemptException;
import com.thiccWallet.FCL.common.exception.NoSuchElementException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.common.util.tokens.TokenService;
import com.thiccWallet.FCL.session.login.dtos.request.LoginRequest;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.UserService;
import com.thiccWallet.FCL.session.login.dtos.responses.PrincipalResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    private UserService userService;
    private TokenService tokenService;

    public LoginController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    //@ResponseStatus(HttpStatus.OK)//returns a principal object for front end
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PrincipalResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest req) {
        if (req.getHeader("Authorization") != null) {
            throw new DuplicateLoginAttemptException("User is already logged in!");
        }

        Optional<User> foundUser = userService.loginUser(loginRequest);

        if (foundUser.isPresent()){
            User authorizedUser = foundUser.get();

            PrincipalResponse payload = new PrincipalResponse(authorizedUser);

            String token = tokenService.generateToken(payload);
            System.out.println("\n\n" + token + "\n\n");

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            headers.set("Content-Type", "application/json");
            headers.set("Access-Control-Expose-Headers", "Authorization");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(payload);

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
