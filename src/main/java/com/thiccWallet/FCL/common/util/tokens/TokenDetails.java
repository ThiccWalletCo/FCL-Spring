package com.thiccWallet.FCL.common.util.tokens;

import com.thiccWallet.FCL.session.login.dtos.responses.PrincipalResponse;

public class TokenDetails {
    private PrincipalResponse user;
    private String walletId;
    private String leagueId;

    public TokenDetails() {
    }

    public TokenDetails(PrincipalResponse user, String walletId, String leagueId) {
        this.user = user;
        this.walletId = walletId;
        this.leagueId = leagueId;
    }

    public PrincipalResponse getUser() {
        return user;
    }

    public void setUser(PrincipalResponse user) {
        this.user = user;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public String toString() {
        return "TokenDetails{" +
                "user=" + user +
                ", walletId='" + walletId + '\'' +
                ", leagueId='" + leagueId + '\'' +
                '}';
    }
}
