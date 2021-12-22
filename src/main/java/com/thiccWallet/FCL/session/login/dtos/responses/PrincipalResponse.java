package com.thiccWallet.FCL.session.login.dtos.responses;

import com.thiccWallet.FCL.endpoints.users.User;

public class PrincipalResponse {

    private String id;
    private String username;

    public PrincipalResponse(){
        super();
    }

    public PrincipalResponse(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public PrincipalResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PrincipalResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
