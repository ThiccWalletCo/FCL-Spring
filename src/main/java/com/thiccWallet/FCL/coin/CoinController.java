package com.thiccWallet.FCL.coin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {

    private CoinService coinService;

    public CoinController(CoinService coinService){
        this.coinService = coinService;
    }

    @GetMapping(produces = "application/json")
    public List<Coin> getPairs() {
        return coinService.getPairs();
    }

}
