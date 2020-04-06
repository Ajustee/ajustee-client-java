package com.ajustee.ConfigCenter;

public abstract class AjusteeClientResponse {
    private final int statusCode;

    AjusteeClientResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public boolean succeeded() {
        return this.statusCode == 200;
    }
}
