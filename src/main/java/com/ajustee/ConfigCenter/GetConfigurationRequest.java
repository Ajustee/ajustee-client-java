package com.ajustee.ConfigCenter;

import java.util.HashMap;

public final class GetConfigurationRequest extends AjusteeClientRequest {
    private final HashMap<String, String> queryParams = new HashMap<String, String>();

    public HashMap<String, String> getQueryParams() {
        return this.queryParams;
    }

    public String getPath() {
        return this.getQueryParam("path");
    }

    public GetConfigurationRequest setPath(String value) {
        return this.setQueryParam("path", value);
    }

    public String getQueryParam(String name) {
        return this.queryParams.get(Utils.requireNonEmpty(name));
    }

    public GetConfigurationRequest setQueryParam(String name, String value) {
        Utils.requireNonEmpty(name);

        if (Utils.isNullOrEmpty(value)) this.queryParams.remove(name);
        else this.queryParams.put(name, value);

        return this;
    }
}
