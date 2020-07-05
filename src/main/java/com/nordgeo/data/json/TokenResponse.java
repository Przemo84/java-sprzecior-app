package com.nordgeo.data.json;

public class TokenResponse<T> {

    private String token;

    private T keeper;

    private Boolean demo;

    public TokenResponse(String token, T keeper, Boolean demo) {
        this.token = token;
        this.keeper = keeper;
        this.demo = demo;
    }

    public String getToken() {
        return token;
    }

    public T getKeeper() {
        return keeper;
    }

    public Boolean getDemo() {
        return demo;
    }
}
