package com.rahmatavg.api.login.model;

public class AuthenticationResponse {
    private final String jwf;

    public String getJwf() {
        return jwf;
    }

    public AuthenticationResponse(String jwf) {
        this.jwf = jwf;
    }
}
