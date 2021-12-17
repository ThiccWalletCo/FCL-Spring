package com.thiccWallet.FCL.endpoints.wallets;

import com.thiccWallet.FCL.common.exception.*;
import com.thiccWallet.FCL.data.coin.CoinService;
import com.thiccWallet.FCL.data.coin.dtos.responses.CoinResponse;
import com.thiccWallet.FCL.endpoints.leagues.League;
import com.thiccWallet.FCL.endpoints.leagues.LeagueService;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.UserRepository;
import com.thiccWallet.FCL.endpoints.users.UserService;
import com.thiccWallet.FCL.endpoints.wallets.dtos.responses.JoinSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private WalletService walletService;
    private LeagueService leagueService;
    private CoinService coinService;
    private UserService userService;

    public WalletController(WalletService walletService, CoinService coinService, LeagueService leagueService, UserService userService) {
        this.walletService = walletService;
        this.coinService = coinService;
        this.leagueService = leagueService;
        this.userService = userService;
    }

    @GetMapping("/coins")
    public List<CoinResponse> getWalletCoins(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("Cannot access wallet, user not logged in!");
        }

        Wallet wallet = (Wallet)session.getAttribute("currentWallet");

        if (wallet == null) {
            throw new NoWalletException("Cannot Access wallet, user has not selected a league.");
        }

        return coinService.getCoinsByWallet(wallet.getWalletID())
                .stream()
                .map(CoinResponse::new)
                .collect(Collectors.toList());
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", value = "/{leagueName}")
    public JoinSuccessResponse joinLeague(@PathVariable String leagueName, HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) throw new NotLoggedInException("Cannot join League, user not logged in.");

        if (leagueName.equals("")) throw new InvalidRequestException("League Name cannot be empty string");

        Optional<League> leagueOptional = leagueService.findLeagueByLeagueName(leagueName);
        if (!leagueOptional.isPresent()) throw new NoSuchElementException("Could not locate a league given: " + leagueName);

        User authUser = (User)session.getAttribute("authorizedUser");
        League currLeague = leagueService.findLeagueByLeagueName(leagueName).get();
        String leagueId = currLeague.getId();
        if(userService.isUserInLeague(leagueId, authUser.getId())){
            throw new InvalidRequestException("user already exists in league");
        }

        return walletService.createWallet(leagueOptional.get(), authUser);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void unJoinLeague(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) throw new NotLoggedInException("Cannot unjoin League, user not logged in.");

        Wallet wallet = (Wallet)session.getAttribute("currentWallet");

        if (wallet == null) throw new NoWalletException("Cannot unjoin league, user has not selected a wallet");

        walletService.deleteWallet(wallet.getWalletID());

        session.setAttribute("currentWallet", null);
        session.setAttribute("currentLeague", null);
    }

    // This endpoint should be hit after /league/select
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/select")
    public void selectWallet(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) throw new NoLeagueException("Cannot select Wallet, user not logged in.");

        User authUser = (User)session.getAttribute("authorizedUser");
        League league = (League)session.getAttribute("currentLeague");

        if (league == null) throw new InvalidRequestException("User does not have a league assigned to their session.");

        Wallet wallet = walletService.selectWalletByIds(league.getId(), authUser.getId());

        session.setAttribute("currentWallet", wallet);
    }


}
