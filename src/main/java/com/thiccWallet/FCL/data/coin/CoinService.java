package com.thiccWallet.FCL.data.coin;

import com.thiccWallet.FCL.common.exception.InsufficientFundsException;
import com.thiccWallet.FCL.common.exception.InvalidRequestException;
import com.thiccWallet.FCL.data.coin.dtos.requests.CoinPurchaseRequest;
import com.thiccWallet.FCL.data.coin.dtos.requests.CoinSellRequest;
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

    //persistence methods
    public boolean buyCoin(Wallet wallet, CoinPurchaseRequest purchaseRequest){
        validBuyAmount(wallet, purchaseRequest); //throws exception if invalid
        Coin currCoin;
        //does user own coin
        if(existsInWallet(wallet, purchaseRequest.getCurrPair())){
            //update existing coin
            currCoin = getCoinInWallet(purchaseRequest.getCurrPair(), wallet);
            currCoin.setAmount(currCoin.getAmount()+purchaseRequest.getAmount());
        } else{
            //create a new coin
            currCoin = new Coin(wallet.getWalletID(), purchaseRequest.getCurrPair(), purchaseRequest.getAmount());
        }
        coinRepo.save(currCoin);
        return true;
    }

    public boolean sellCoin(Wallet wallet, CoinPurchaseRequest coinSellReq) {
        validSellAmount(wallet, coinSellReq);

        Coin currCoin;
        //update existing coin
        currCoin = getCoinInWallet(coinSellReq.getCurrPair(), wallet);
        currCoin.setAmount(currCoin.getAmount() - coinSellReq.getAmount());

        if(currCoin.getAmount()==0){
            coinRepo.delete(currCoin);
        } else{
            coinRepo.save(currCoin);
        }
        return true;
    }

    //verification methods

    //determines if the user can buy the coin.
    public boolean validBuyAmount(Wallet wallet, CoinPurchaseRequest coinPurchReq){
        //check if coin they want to buy exists
        isCurrencyPairValid(coinPurchReq.getCurrPair());

        //makes sure value is positive
        if(coinPurchReq.getAmount() <= 0){
            throw new InvalidRequestException("amount is below 0");
        }

        //check if they're too poor to buy
        double usdAmount = getUsdAmount(coinPurchReq);
        if(wallet.getCashBalance() < usdAmount ){
            throw new InsufficientFundsException("get more money and come back");
        }

        return true;
    }

    public double getUsdAmount(CoinPurchaseRequest coinPurchReq){
        return (coinPurchReq.getAmount())*(coinDAO.valueOf(coinPurchReq.getCurrPair()));
    }

    public boolean validSellAmount(Wallet wallet, CoinPurchaseRequest sellCoin){
        //check if coin they want to buy exists
        isCurrencyPairValid(sellCoin.getCurrPair());

        //makes sure value is positive
        if(sellCoin.getAmount() <= 0){
            throw new InvalidRequestException("amount is below 0");
        }

        //check that they have the coin
        if(!existsInWallet(wallet, sellCoin.getCurrPair())) {
            throw new NoSuchElementException("You don't have any of that coin!");
        }

        //check that they have enough of the coin
        double ownedAmount = getCoinInWallet(sellCoin.getCurrPair(), wallet).getAmount();
        if(ownedAmount < sellCoin.getAmount()) {
            throw new InsufficientFundsException("You can't sell more than you own dummy. Try again");
        }

        return true;
    }

    //checks if coin exists in a passed in wallet
    public boolean existsInWallet(Wallet wallet, String currPair){
        List<Coin> ownedCoins = coinRepo.findCoinsByWalletId(wallet.getWalletID());
        return ownedCoins.stream().anyMatch(c -> c.getCoinId().getCurrPair().equals(currPair));
    }

    public boolean isCurrencyPairValid(String currPair){
        if(!coinPairs.stream().anyMatch(c -> c.equals(currPair))){
            throw new NoSuchElementException("currency pair doesn't exist");
        }
        return true;
//        delete me
//        for(String pair : coinPairs){
//            if(currPair.equals(pair)){
//                return true;
//            }
//        }
//        return false;
    }

    //returns the amount of a coin in a wallet. returns -1 if not found
    public Coin getCoinInWallet(String currPair, Wallet wallet){
        List<Coin> ownedCoins = coinRepo.findCoinsByWalletId(wallet.getWalletID());
        for(Coin coin : ownedCoins){
            if(currPair.equals(coin.getCoinId().getCurrPair())){
                return coin;
            }
        }
        return null;
    }


    public double calculateCoinValue(List<Coin> walletCoins) {

        double total = 0;

        for (Coin c : walletCoins) {
            total += coinDAO.valueOf(c.getCoinId().getCurrPair()) * c.getAmount();
        }

        return total;
    }
}
