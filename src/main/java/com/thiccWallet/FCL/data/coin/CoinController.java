package com.thiccWallet.FCL.data.coin;

import com.thiccWallet.FCL.common.exception.NoWalletException;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.common.util.tokens.TokenDetails;
import com.thiccWallet.FCL.common.util.tokens.TokenService;
import com.thiccWallet.FCL.data.coin.dtos.requests.CoinPurchaseRequest;
import com.thiccWallet.FCL.endpoints.wallets.Wallet;
import com.thiccWallet.FCL.endpoints.wallets.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/coin")
public class CoinController {

    private CoinService coinService;
    private WalletService walletService;
    private TokenService tokenService;

    public CoinController(CoinService coinService, WalletService walletService, TokenService tokenService){
        this.coinService = coinService;
        this.walletService = walletService;
        this.tokenService = tokenService;
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
    public void buyCoin(@RequestBody CoinPurchaseRequest coinPurchReq, @RequestHeader("Authorization") String token){
        if (token == null || token.equals("")) throw new NotLoggedInException("Cannot buy coin. You're not logged in!.");

        TokenDetails authDeets = tokenService.extractAdvancedTokenDetails(token);
        Wallet wallet = walletService.getWalletById(authDeets.getWalletId()).get();

        if(wallet == null) {
            throw new NoWalletException("User has not selected a current wallet.");
        }

        //update usdBalance (total wallet balance in usd)
        if(coinService.buyCoin(wallet, coinPurchReq)){//
            // persist the update
            wallet.setCashBalance(wallet.getCashBalance() - coinService.getUsdAmount(coinPurchReq));
           walletService.updateWallet(wallet);

        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/sell", consumes = "application/json")
    public void sellCoin(@RequestBody CoinPurchaseRequest coinPurchReq, @RequestHeader("Authorization") String token){
        if (token == null || token.equals("")) throw new NotLoggedInException("Cannot buy coin. You're not logged in!.");

        TokenDetails authDeets = tokenService.extractAdvancedTokenDetails(token);
        Wallet wallet = walletService.getWalletById(authDeets.getWalletId()).get();

        if(wallet == null) {
            throw new NoWalletException("User has not selected a current wallet.");
        }
        if(coinService.sellCoin(wallet, coinPurchReq)){//
            // persist the update
            wallet.setCashBalance(wallet.getCashBalance() + coinService.getUsdAmount(coinPurchReq));
            walletService.updateWallet(wallet);
        }

    }



}
