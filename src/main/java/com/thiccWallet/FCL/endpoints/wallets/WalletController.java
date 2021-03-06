package com.thiccWallet.FCL.endpoints.wallets;

import com.thiccWallet.FCL.common.exception.*;
import com.thiccWallet.FCL.common.util.tokens.TokenService;
import com.thiccWallet.FCL.data.coin.Coin;
import com.thiccWallet.FCL.data.coin.CoinService;
import com.thiccWallet.FCL.data.coin.dtos.responses.CoinResponse;
import com.thiccWallet.FCL.endpoints.leagues.League;
import com.thiccWallet.FCL.endpoints.leagues.LeagueService;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.UserRepository;
import com.thiccWallet.FCL.endpoints.users.UserService;
import com.thiccWallet.FCL.endpoints.wallets.dtos.request.IndiWalletRequest;
import com.thiccWallet.FCL.endpoints.wallets.dtos.responses.JoinSuccessResponse;
import com.thiccWallet.FCL.endpoints.wallets.dtos.responses.WalletResponse;
import com.thiccWallet.FCL.session.login.dtos.responses.PrincipalResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/wallet")
public class WalletController {

    private WalletService walletService;
    private LeagueService leagueService;
    private CoinService coinService;
    private UserService userService;
    private TokenService tokenService;

    public WalletController(WalletService walletService, CoinService coinService, LeagueService leagueService, UserService userService, TokenService tokenService) {
        this.walletService = walletService;
        this.coinService = coinService;
        this.leagueService = leagueService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WalletResponse getWalletCoins(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("Cannot access wallet, user not logged in!");
        }

        User authUser = (User)session.getAttribute("authorizedUser");
        Wallet wallet = (Wallet)session.getAttribute("currentWallet");

        if (wallet == null) {
            throw new NoWalletException("Cannot Access wallet, user has not selected a league.");
        }

        List<Coin> walletCoins = coinService.getCoinsByWallet(wallet.getWalletID())
                .stream()
                .map(Coin::new)
                .collect(Collectors.toList());

        double walletCoinUSDValue = coinService.calculateCoinValue(walletCoins);

        List<CoinResponse> coinResponses = walletCoins
                .stream()
                .map(CoinResponse::new)
                .collect(Collectors.toList());

        return new WalletResponse(
                authUser.getUsername(),
                walletCoinUSDValue + wallet.getCashBalance(),
                wallet.getCashBalance(),
                coinResponses);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", value = "/{leagueName}")
    public JoinSuccessResponse joinLeague(@PathVariable String leagueName, @RequestHeader("Authorization") String token) {
        if (token == null || token.equals("")) throw new NotLoggedInException("Cannot join League, user not logged in.");

        if (leagueName.equals("")) throw new InvalidRequestException("League Name cannot be empty string");

        Optional<League> leagueOptional = leagueService.findLeagueByLeagueName(leagueName);
        if (!leagueOptional.isPresent()) throw new NoSuchElementException("Could not locate a league given: " + leagueName);

        PrincipalResponse authUser = tokenService.extractTokenDetails(token);

        League currLeague = leagueService.findLeagueByLeagueName(leagueName).get();
        String leagueId = currLeague.getId();
        if(userService.isUserInLeague(leagueId, authUser.getId())){
            throw new InvalidRequestException("user already exists in league");
        }

        return walletService.createWallet(leagueOptional.get(), userService.getUserById(authUser.getId()).get());
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
    @PostMapping("/select/{leagueName}")
    public ResponseEntity <Void> selectWallet(@PathVariable String leagueName, @RequestHeader("Authorization") String token) {
        if (token == null || token.equals("")) throw new NoLeagueException("Cannot select Wallet, user not logged in.");

        PrincipalResponse authUser = tokenService.extractTokenDetails(token);
        //HttpSession session = req.getSession(false);


        // User authUser = (User)session.getAttribute("authorizedUser");
        League league = leagueService.findLeagueByLeagueName(leagueName).get();

        if (league == null) throw new InvalidRequestException("User does not have a league assigned to their session.");

        Wallet wallet = walletService.selectWalletByIds(league.getId(), authUser.getId());

        // PrincipalResponse principalResponse = new PrincipalResponse(authUser);
        String advancedToken = tokenService.generateAdvancedToken(authUser, league.getId(), wallet.getWalletID());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", advancedToken);
        headers.set("Access-Control-Expose-Headers", "Authorization");

        return ResponseEntity.ok()
                .headers(headers).body(null);
    }

    @PostMapping("/get")
    public WalletResponse getIndiWallet (@RequestBody IndiWalletRequest req){
        Wallet wallet = walletService.getIndiWallet(req);

        List<Coin> walletCoins = coinService.getCoinsByWallet(wallet.getWalletID())
                .stream()
                .map(Coin::new)
                .collect(Collectors.toList());

        double walletCoinUSDValue = coinService.calculateCoinValue(walletCoins);

        List<CoinResponse> coinResponses = walletCoins
                .stream()
                .map(CoinResponse::new)
                .collect(Collectors.toList());

        return new WalletResponse(
                req.getUsername(),
                walletCoinUSDValue + wallet.getCashBalance(),
                wallet.getCashBalance(),
                coinResponses);
    }

//        List<Coin> coins = coinService.getCoinsByWallet(wallet.getWalletID());
//        return new WalletResponse(wallet.getOwner().getUsername(), walletService.get, wallet.getCashBalance(), )
       // return new WalletResponse(walletService.getIndiWallet(req))



}
