package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.common.exception.DuplicateCredentialsException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.endpoints.leagues.dtos.requests.LeagueCreationRequest;
import com.thiccWallet.FCL.endpoints.leagues.dtos.responses.LeagueCreatedResponse;
import com.thiccWallet.FCL.endpoints.leagues.dtos.responses.LeagueResponse;
import com.thiccWallet.FCL.endpoints.users.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/league")
public class LeagueController {

    private LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    //Get all leagues
    @GetMapping(produces = "application/json")
    public List<LeagueResponse> getLeagues() {
        return leagueService.getAllLeagues();
    }

    // Create a league
    @PostMapping(consumes = "application/json", produces = "application/json")
    public LeagueCreatedResponse createLeague(@RequestBody LeagueCreationRequest creationRequest, HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("Can't create League, user is not logged in.");
        }

        User authUser = (User)session.getAttribute("authorizedUser");

        return leagueService.createLeague(creationRequest, authUser);
    }



}
