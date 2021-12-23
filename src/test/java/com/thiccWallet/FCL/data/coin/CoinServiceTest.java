package com.thiccWallet.FCL.data.coin;

import org.junit.Before;

import java.util.List;

import static org.mockito.Mockito.mock;

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
        List<Coin> mockCurrencyPairList = new ArrayList<>();
        mockCurrencyPairList.add(new Coin("BTC-USD", 0.0, null));
        mockCurrencyPairList.add(new Coin("ETH-USD", 0.0, null));
        mockCurrencyPairList.add(new Coin("USDT-USD", 0.0, null));

        when(mockCoinbaseDAO.getAllCoins()).thenReturn(mockCurrencyPairList);

        sut = new CoinService(mockCoinDAO, mockCoinbaseDAO);
    }

    @After
    public void testCaseCleanUp() {
        sut = null;
    }

}
