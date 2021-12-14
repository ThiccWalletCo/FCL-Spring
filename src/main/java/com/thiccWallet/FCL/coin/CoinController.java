package com.thiccWallet.FCL.coin;

import com.thiccWallet.FCL.coin.dtos.responses.CoinResponse;
import com.thiccWallet.FCL.common.exception.NotLoggedInException;
import com.thiccWallet.FCL.user.dtos.requests.UserCreationRequest;
import com.thiccWallet.FCL.user.dtos.responses.UserCreatedResponse;
import com.thiccWallet.FCL.user.dtos.responses.UserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coin")
public class CoinController {

    private CoinService coinService;
    //private String walletId;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping(produces = "application/json")
    public List<CoinResponse> getCoins() {
        return coinService.getAllCoins();
    }

    @RequestMapping("/find")
    @ResponseStatus(HttpStatus.FOUND)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Optional<List<Coin>> findCoinsByWalletId(Coin walletId) {
        return null;
    }

}
