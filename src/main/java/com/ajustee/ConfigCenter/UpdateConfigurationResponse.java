package com.ajustee.ConfigCenter;

public final class UpdateConfigurationResponse extends AjusteeClientResponse {
    UpdateConfigurationResponse(int statusCode) {
        super(statusCode);
    }

    @Override
    public boolean succeeded() {
        return this.getStatusCode() == 204;
    }
}
