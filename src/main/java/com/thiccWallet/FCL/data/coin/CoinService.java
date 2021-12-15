package com.thiccWallet.FCL.data.coin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoinService {
    private final CoinRepository coinRepo;
    public CoinService(CoinRepository coinRepo){
        this.coinRepo = coinRepo;
    }

    @Transactional
    public List<Coin> getPairs(){
        return coinRepo.findAllCoins();
    }


    //verification methods


}
