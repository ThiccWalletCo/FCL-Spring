package com.thiccWallet.FCL.data.coin;

import com.thiccWallet.FCL.common.exception.InsufficientFundsException;
import com.thiccWallet.FCL.data.coin.dtos.requests.CoinPurchaseRequest;
import com.thiccWallet.FCL.endpoints.users.User;
import com.thiccWallet.FCL.endpoints.users.dtos.responses.UserResponse;
import com.thiccWallet.FCL.endpoints.wallets.Wallet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoinService {
    private final CoinRepository coinRepo;
    private List<String> coinPairs;
    private CoinbaseDAO coinDAO;
    public CoinService(CoinRepository coinRepo){
        this.coinRepo = coinRepo;
        coinDAO = new CoinbaseDAO();
        coinPairs = coinDAO.getAllCoins();
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
        return coinRepo.findCoinsByWalletId(walletId);
    }

    public boolean validBuyAmount(Wallet wallet, CoinPurchaseRequest coinDTO){
        //check if coin they want to buy exists
        if(!isCurrencyPairValid(coinDTO.getCurrPair())){
            throw new NoSuchElementException("currency pair doesn't exist");
        }

        //check if they're too poor to buy
        double usdAmount =(coinDTO.getAmount())*(coinDAO.valueOf(coinDTO.getCurrPair()));
        if(wallet.getCashBalance() < usdAmount ){
            throw new InsufficientFundsException("get more money and come back");
        }

        return true;
    }

    public boolean validSellAmount(){
        return false;
    }

    //checks if coin exists in a passed in wallet
    public boolean existsInWallet(){
        //TODO: implement me
        return false;
    }

    //verification methods
    public boolean isCurrencyPairValid(String currPair){
        return coinPairs.stream().anyMatch(c -> c.equals(currPair));
//        delete me
//        for(String pair : coinPairs){
//            if(currPair.equals(pair)){
//                return true;
//            }
//        }
//        return false;
    }

}
