package com.ajustee.ConfigCenter;

public final class UpdateConfigurationRequest extends AjusteeClientRequest {
    private final String path;
    private final Object value;

    public UpdateConfigurationRequest(String path, Object value) {
        this.path = Utils.requireNonEmpty(path);
        this.value = Utils.requireNonNull(value);
    }

    public String getPath() {
        return this.path;
    }

    public Object getValue() {
        return this.value;
    }
}
