package com.thiccWallet.FCL.endpoints.wallets.dtos.request;

public class IndiWalletRequest {
    private String username;
    private String leagueName;

    public IndiWalletRequest(String username, String leagueName) {
        this.username = username;
        this.leagueName = leagueName;
    }

    public IndiWalletRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    @Override
    public String toString() {
        return "IndiWalletRequest{" +
                "username='" + username + '\'' +
                ", leagueName='" + leagueName + '\'' +
                '}';
    }
}
