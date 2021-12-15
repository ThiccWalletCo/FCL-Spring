package com.thiccWallet.FCL.data.coin;

import org.springframework.web.bind.annotation.*;

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
        return coinService.getCoin(walletId, currPair);
    }

    @GetMapping(value = "/{walletId}", produces = "application/json")
    public List<Coin> getCoinsByWallet(@PathVariable String walletId) {
        return coinService.getCoinsByWallet(walletId);
    }
}
