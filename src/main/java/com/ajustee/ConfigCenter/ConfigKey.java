package com.ajustee.ConfigCenter;

public final class ConfigKey {
    private String path;
    private ConfigKeyType dataType;
    private Object value;

    public ConfigKey() { }
    
    public ConfigKey(String path, ConfigKeyType dataType, Object value) {
        this.path = path;
        this.dataType = dataType;
        this.value = value;
    }

    public String getPath() {
        return this.path;
    }

    public ConfigKeyType getDataType() {
        return this.dataType;
    }

    public Object getValue() {
        return this.value;
    }
}
