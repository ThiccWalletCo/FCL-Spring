package com.thiccWallet.FCL.coin;

import com.thiccWallet.FCL.coin.dtos.responses.CoinResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinService {

    private final CoinRepository coinRepo;
    public CoinService(CoinRepository coinRepo) {
        this.coinRepo = coinRepo;
    }

    @Transactional(readOnly = true)
    public List<CoinResponse> getAllCoins() {
        List<CoinResponse> coins = ((Collection<Coin>) coinRepo.findAll())
                .stream()
                .map(CoinResponse::new)
                .collect(Collectors.toList());

        if (coins.isEmpty()) {
            // TODO - Create custom exceptions
            // throw new ResourceNotFoundException();
        }
        return coins;
    }

    @Transactional
    public List<CoinResponse> findCoinByWalletId(Coin coin){
        List<CoinResponse> coins = ((Collection<Coin>) coinRepo.findById(coin.getWalletId()).get())
                .stream()
                .map(CoinResponse::new)
                .collect(Collectors.toList());

        if (coins.isEmpty()) {
            // TODO - Create custom exceptions
            // throw new ResourceNotFoundException();
        }
        return coins;
    }

}
