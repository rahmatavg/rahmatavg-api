package com.rahmatavg.api.login.model;

public class GenerateToken {
    private String token;
    private Long userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public GenerateToken() {
    }

    @Override
    public String toString() {
        return "GenerateToken{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
