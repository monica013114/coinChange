package com.coinchange.model;

import java.util.Map;

public class ChangeResponse {
    private String message;
    private Map<String, Integer> coins;

    public ChangeResponse() {}

    public ChangeResponse(String message, Map<String, Integer> coins) {
        this.message = message;
        this.coins = coins;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Integer> getCoins() {
        return coins;
    }
}