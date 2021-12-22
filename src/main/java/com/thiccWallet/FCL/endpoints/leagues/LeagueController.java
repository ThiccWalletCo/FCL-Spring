package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.common.exception.*;
import com.thiccWallet.FCL.common.util.tokens.TokenDetails;
import com.thiccWallet.FCL.common.util.tokens.TokenService;
import com.thiccWallet.FCL.endpoints.leagues.dtos.requests.LeagueCreationRequest;
import com.thiccWallet.FCL.endpoints.leagues.dtos.responses.LeagueCreatedResponse;
import com.thiccWallet.FCL.endpoints.leagues.dtos.responses.LeagueResponse;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.UserService;
import com.thiccWallet.FCL.session.login.dtos.responses.PrincipalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/league")
public class LeagueController {

    private LeagueService leagueService;
    private UserService userService;
    private TokenService tokenService;

    public LeagueController(LeagueService leagueService, UserService userService, TokenService tokenService) {
        this.leagueService = leagueService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    //Get all leagues
    @GetMapping(produces = "application/json")
    public List<LeagueResponse> getLeagues() {
        return leagueService.getAllLeagues();
    }

    @GetMapping(value="/user={userId}", produces = "application/json")
    public List<LeagueResponse> getLeaguesByUser(@PathVariable String userId) {
        return leagueService.getAllLeaguesByUser(userId);
    }

    // Create a league
    @PostMapping(consumes = "application/json", produces = "application/json")
    public LeagueCreatedResponse createLeague(@RequestBody LeagueCreationRequest creationRequest, HttpServletRequest req, @RequestHeader("Authorization") String token) {
        if (token == null || token.equals("")) {
            throw new NotLoggedInException("Can't create League, user is not logged in.");
        }

        PrincipalResponse authDetails;
        try {
            authDetails = tokenService.extractAdvancedTokenDetails(token).getUser();
        } catch (Exception e) {
            authDetails = tokenService.extractTokenDetails(token);
        }
        User authUser = userService.getUserById(authDetails.getId()).get();


        return leagueService.createLeague(creationRequest, authUser);
    }

    // "Sign-in" to league
    // this endpoint should be hit right before /wallet/select
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/select/{leagueName}")
    public void selectLeague(@PathVariable String leagueName, HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("Cannot select league, user not logged in!");
        }

        if (leagueName.equals("")) {
            throw new InvalidRequestException("League name cannot be empty string");
        }

        Optional<League> leagueOptional = leagueService.findLeagueByLeagueName(leagueName);
        if (!leagueOptional.isPresent()) {
            throw new NoSuchElementException("Could not locate a league given: " + leagueName);
        }

        User authUser = (User)session.getAttribute("authorizedUser");
        League league = leagueOptional.get();

        List<User> joinedUsers = userService.getUsersInLeague(league.getId());

        if (!joinedUsers.stream().anyMatch(user -> user.equals(authUser))){
            throw new NotJoinedException("User has not joined this league!");
        }

        session.setAttribute("currentLeague", league);


    }

    @GetMapping("/name")
    public ResponseEntity<Void> checkLeagueNameAvailability(@RequestParam String name) {
        return leagueService.isLeagueNameAvailable(name) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
