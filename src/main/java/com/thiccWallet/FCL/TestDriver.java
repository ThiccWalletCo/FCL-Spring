package com.thiccWallet.FCL;

import com.thiccWallet.FCL.data.coin.CoinbaseDAO;

import java.util.List;

//delete me!
public class TestDriver {
    public static void main(String[] args) {
        CoinbaseDAO coinbase = new CoinbaseDAO();

        System.out.println(coinbase.valueOf("BTC-USD"));
        String currencies = coinbase.getSupportedCurrencies_V2();
        String trading_pairs = coinbase.getTradingPairs_E();

        List<String> coins = coinbase.getAllCoins();
        System.out.println(coins);

    }
}
