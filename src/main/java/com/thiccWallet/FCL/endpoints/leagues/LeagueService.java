package com.thiccWallet.FCL.endpoints.leagues;

import com.thiccWallet.FCL.common.exception.DuplicateCredentialsException;
import com.thiccWallet.FCL.common.exception.InvalidRequestException;
import com.thiccWallet.FCL.endpoints.leagues.dtos.requests.LeagueCreationRequest;
import com.thiccWallet.FCL.endpoints.leagues.dtos.responses.LeagueCreatedResponse;
import com.thiccWallet.FCL.endpoints.leagues.dtos.responses.LeagueResponse;
import com.thiccWallet.FCL.endpoints.users.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    //creates a new league with a name and initial amount
    public LeagueCreatedResponse createLeague(LeagueCreationRequest creationRequest, User authUser){
        if (!isLeagueValid(creationRequest)) {
            throw new InvalidRequestException("Invalid Credentials, either empty name field or balance < 1");
        }

        if (leagueRepo.findLeagueByLeagueName(creationRequest.getName()).isPresent()) {
            throw new DuplicateCredentialsException("League name is already taken.");
        }

        League authLeague = new League(creationRequest, authUser);

        return new LeagueCreatedResponse(leagueRepo.save(authLeague));
    }

    //deletes a league of a given name
    public boolean deleteLeague(String leagueName){
        return false;
    }

    //validation methods:
    //makes sure league name is valid syntax
    private boolean isLeagueValid(LeagueCreationRequest creationRequest){
        if (creationRequest.getName() == null || creationRequest.getName().trim().equals("")) return false;
        return creationRequest.getInitialBalance() >= 1;
    }

    public Optional<League> findLeagueByLeagueName(String leagueName){
        return leagueRepo.findLeagueByLeagueName(leagueName);
    }

}