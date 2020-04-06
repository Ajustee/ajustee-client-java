package com.ajustee.ConfigCenter;

public final class AjusteeClientOptions {
    private final static String DefaultApiUri = "https://api.ajustee.com/fo";

    private final String apiUri;
    private final String applicationId;

    public AjusteeClientOptions(String applicationId) {
        this(DefaultApiUri, applicationId);
    }

    public AjusteeClientOptions(String apiUri, String applicationId) {
        Utils.requireNonEmpty(apiUri);
        this.apiUri = apiUri.endsWith("/") ? apiUri.substring(0, apiUri.length() - 1) : apiUri;
        this.applicationId = Utils.requireNonEmpty(applicationId);
    }

    public String getApiUri() {
        return this.apiUri;
    }

    public String getApplicationId() {
        return this.applicationId;
    }
}
