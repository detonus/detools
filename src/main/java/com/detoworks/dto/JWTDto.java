package com.detoworks.dto;

/**
 * Created by Banach on 2017-01-29.
 */
public class JWTDto {

    private String token;
    private String decoded;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDecoded() {
        return decoded;
    }

    public void setDecoded(String decoded) {
        this.decoded = decoded;
    }
}
