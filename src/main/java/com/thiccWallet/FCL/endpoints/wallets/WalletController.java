package com.thiccWallet.FCL.endpoints.wallets;

import com.thiccWallet.FCL.common.exception.NoWalletException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.data.coin.Coin;
import com.thiccWallet.FCL.data.coin.CoinService;
import com.thiccWallet.FCL.data.coin.dtos.responses.CoinResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public List<CoinResponse> getWalletCoins(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null) {
            throw new NotLoggedInException("Cannot access wallet, user not logged in!");
        }

        Wallet wallet = (Wallet)session.getAttribute("wallet");

        if (wallet == null) {
            throw new NoWalletException("Cannot Access wallet, user has not selected a league.");
        }

        return wallet.getWalletCoins()
                .stream()
                .map(CoinResponse::new)
                .collect(Collectors.toList());
    }


}
