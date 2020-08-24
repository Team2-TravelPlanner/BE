package com.laioffer.travelplanner.model.common;

public class AuthenticationResponse {

    private String message;

    private String email;

    private String jwt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthenticationResponse(String message, String email, String jwt) {
        this.message = message;
        this.email = email;
        this.jwt = jwt;
    }
}
