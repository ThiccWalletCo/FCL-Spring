package com.thiccWallet.FCL.data.coin;

import com.thiccWallet.FCL.common.exception.NoWalletException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.data.coin.dtos.requests.CoinPurchaseRequest;
import com.thiccWallet.FCL.endpoints.wallets.Wallet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinController {

    private CoinService coinService;

    public CoinController(CoinService coinService){
        this.coinService = coinService;
    }

    @GetMapping(produces = "application/json")
    public List<Coin> getPairs() {
        return coinService.getPairs();
    }

    @GetMapping(value = "/{walletId}/{currPair}", produces = "application/json")
    public Coin getCoin(@PathVariable String walletId, @PathVariable String currPair){
        return coinService.getCoin(new CoinId(currPair, walletId)).get();
    }

    @GetMapping(value = "/{walletId}", produces = "application/json")
    public List<Coin> getCoinsByWallet(@PathVariable String walletId) {
        return coinService.getCoinsByWallet(walletId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/buy", consumes = "application/json")
    public void buyCoin(@RequestBody CoinPurchaseRequest buyCoin, HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session == null) throw new NotLoggedInException("Cannot buy coin. You're not logged in!.");
        Wallet wallet = (Wallet) session.getAttribute("currentWallet");
        if(wallet == null) {
            throw new NoWalletException("User has not selected a current wallet.");
        }
        if(coinService.validBuyAmount(wallet, buyCoin)){

        }
    }

    //insert coins method


    //update coins

}
