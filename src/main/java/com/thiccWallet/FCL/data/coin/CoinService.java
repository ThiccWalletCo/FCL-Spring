package com.thiccWallet.FCL.data.coin;

import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.dtos.responses.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoinService {
    private final CoinRepository coinRepo;
    public CoinService(CoinRepository coinRepo){
        this.coinRepo = coinRepo;
    }

    @Transactional
    public List<Coin> getPairs(){
        List<Coin> coins = ((Collection<Coin>) coinRepo.findAll())
                .stream()
                .map(Coin::new)
                .collect(Collectors.toList());
        return coins;
    }

    @Transactional
    public Optional<Coin> getCoin(CoinId coinId) {
        return coinRepo.findById(coinId);
    }

    public List<Coin> getCoinsByWallet(String walletId) {
        //return coinRepo.findCoinsByWalletId(walletId);
        return null;
    }


    //verification methods


}
