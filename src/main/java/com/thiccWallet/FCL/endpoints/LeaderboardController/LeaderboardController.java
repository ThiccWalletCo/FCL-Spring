package com.thiccWallet.FCL.endpoints.LeaderboardController;

import com.thiccWallet.FCL.common.exception.NoLeagueException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.data.coin.Coin;
import com.thiccWallet.FCL.data.coin.CoinService;
import com.thiccWallet.FCL.endpoints.LeaderboardController.dtos.responses.LeaderboardResponse;
import com.thiccWallet.FCL.endpoints.leagues.League;
import com.thiccWallet.FCL.endpoints.leagues.LeagueController;
import com.thiccWallet.FCL.endpoints.leagues.LeagueService;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.UserService;
import com.thiccWallet.FCL.endpoints.wallets.Wallet;
import com.thiccWallet.FCL.endpoints.wallets.WalletService;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private WalletService walletService;
    private UserService userService;
    private CoinService coinService;

    public LeaderboardController(WalletService walletService, UserService userService, CoinService coinService) {
        this.walletService = walletService;
        this.userService = userService;
        this.coinService = coinService;
    }

    @GetMapping(produces = "application/json")
    public List<LeaderboardResponse> getLeaderboard(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("Can't access League, user is not logged in.");
        }

        League league = (League)session.getAttribute("currentLeague");

        if (league == null) {
            throw new NoLeagueException("User does not have a current league assigned");
        }

        List<Wallet> wallets = walletService.getWalletsInLeague(league.getId());

        List<LeaderboardResponse> response = new LinkedList<>();

        for (Wallet w : wallets) {
            List<Coin> coins = coinService.getCoinsByWallet(w.getWalletID());
            double amount = coinService.calculateCoinValue(coins) + w.getCashBalance();

            String username = w.getOwner().getUsername();

            response.add(new LeaderboardResponse(username, amount));

        }

        response = response.stream()
                .sorted((lr1, lr2) -> (int)Math.signum(lr2.getBalance() - lr1.getBalance()))
                .collect(Collectors.toList());

        return response;

    }
}
