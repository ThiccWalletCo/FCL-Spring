package com.thiccWallet.FCL;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange;
import com.thiccWallet.FCL.data.coin.Coin;
import com.thiccWallet.FCL.data.coin.CoinbaseDAO;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;

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
