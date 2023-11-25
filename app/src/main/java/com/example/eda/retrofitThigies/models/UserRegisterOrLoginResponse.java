package com.example.eda.retrofitThigies.models;

public class UserRegisterOrLoginResponse {
    private String token;
    public UserRegisterOrLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
