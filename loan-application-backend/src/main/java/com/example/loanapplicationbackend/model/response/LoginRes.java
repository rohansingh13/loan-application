package com.example.loanapplicationbackend.model.response;

import com.example.loanapplicationbackend.model.User;
import lombok.Builder;

@Builder
public class LoginRes {
    private User user;
    private String jwtToken;


    public LoginRes(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
