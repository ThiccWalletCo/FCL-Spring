package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.endpoints.leagues.dtos.LeagueResponse;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.dtos.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepo;

    public LeagueService(LeagueRepository leagueRepo) {
        this.leagueRepo = leagueRepo;
    }

    //returns all leagues, overloaded with user_id
    public List<LeagueResponse> getAllLeagues(){
        List<LeagueResponse> leagues = ((Collection<League>) leagueRepo.findAll())
                .stream()
                .map(LeagueResponse::new)
                .collect(Collectors.toList());

        return leagues;
    }

    //adds a user to specified league
    public boolean addUserToLeague(User user, League league){
        return false;
    }

    //creates a new league with a name and initial amount
    public League createLeague(String name, double initialAmount){
        //TODO: implement me
        return null;
    }

    //deletes a league of a given name
    public boolean deleteLeague(String leagueName){
        return false;
    }

    //validation methods:
    //makes sure league name is valid syntax
    private boolean isLeagueValid(){
        return false;
    }

    //returns true if the league name does not exist in database
    public boolean isLeagueNameAvailable(String leagueName){
        return false;
    }

}