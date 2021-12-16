package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.endpoints.leagues.dtos.LeagueResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<LeagueResponse> getLeagues() { return leagueService.getAllLeagues();}
}
