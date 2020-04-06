package com.ajustee.ConfigCenter;

public final class GetConfigurationResponse extends AjusteeClientResponse {
    private final ConfigKey[] configKeys;

    GetConfigurationResponse(int statusCode) {
        super(statusCode);
        this.configKeys = null;
    }

    GetConfigurationResponse(ConfigKey[] configKeys) {
        super(200);
        this.configKeys = configKeys;
    }

    public ConfigKey[] getConfigKeys() {
        return this.configKeys;
    }
}
