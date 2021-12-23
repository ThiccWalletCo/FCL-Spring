package com.thiccWallet.FCL.data.coin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CoinServiceTest {
    private CoinRepository mockCoinRepo;
    private CoinbaseDAO mockCoinDAO;

    private CoinService sut;

    public CoinServiceTest() {
    }

    @Before
    public void testCaseSetup() {
        mockCoinDAO = mock(CoinbaseDAO.class);
        mockCoinRepo = mock(CoinRepository.class);

        //have to mock getAllCoins in CoinService Constructor
        List<String> mockCurrencyPairList = new ArrayList<>();
        mockCurrencyPairList.add("BTC-USD");
        mockCurrencyPairList.add("ETH-USD");
        mockCurrencyPairList.add("USDT-USD");

        when(mockCoinDAO.getAllCoins()).thenReturn(mockCurrencyPairList);

        sut = new CoinService(mockCoinRepo);
    }

    @After
    public void testCaseCleanUp() {
        sut = null;
    }

    @Test
    public void test_getPairs_returnsNotNull() {

        // Arrange
        List<Coin> mockCoinList = new ArrayList<>();
        mockCoinList.add(new Coin("WalletId", "Curr_pair", 1));
        mockCoinList.add(new Coin("WalletId", "Curr_pair", 1));
        mockCoinList.add(new Coin("WalletId", "Curr_pair", 1));

        when(mockCoinRepo.findAll()).thenReturn(mockCoinList);

        // Act
        List<Coin> actualResult = sut.getPairs();

        // Assert
        verify(mockCoinRepo, times(1)).findAll();
        Assert.assertNotNull("Expected List to be returned", actualResult);

    }

    @Test
    public void test_getCoin_returnsNotNullInOptional_GivenValidCoinId() {

        // Arrange
        CoinId validCoinId = new CoinId("Curr_pair", "WalletId");

        Optional<Coin> validCoinOptional = Optional.of(new Coin("WalletId", "Curr_pair", 1));

        when(mockCoinRepo.findById(validCoinId)).thenReturn(validCoinOptional);

        // Act
        Optional<Coin> actualResult = sut.getCoin(validCoinId);

        // Assert
        verify(mockCoinRepo, times(1)).findById(validCoinId);
        Assert.assertTrue("Expected Optional with present item", actualResult.isPresent());

    }

}
