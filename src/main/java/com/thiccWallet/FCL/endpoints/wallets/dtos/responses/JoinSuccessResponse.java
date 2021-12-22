package com.thiccWallet.FCL.endpoints.wallets.dtos.responses;

import com.thiccWallet.FCL.endpoints.wallets.Wallet;

import java.time.LocalDateTime;

public class JoinSuccessResponse {
    private String username;
    private String leagueName;
    private LocalDateTime dateCreated;

    public JoinSuccessResponse() {
    }

    public JoinSuccessResponse(String username, String leagueName, LocalDateTime dateCreated) {
        this.username = username;
        this.leagueName = leagueName;
        this.dateCreated = dateCreated;
    }

    public JoinSuccessResponse(Wallet wallet) {
        this.username = wallet.getOwner().getUsername();
        this.leagueName = wallet.getLeague().getLeagueName();
        this.dateCreated = wallet.getDateCreated();
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "JoinSuccessResponse{" +
                "username='" + username + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
