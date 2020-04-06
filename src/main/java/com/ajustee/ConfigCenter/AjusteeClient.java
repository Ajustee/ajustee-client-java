package com.ajustee.ConfigCenter;

import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

public final class AjusteeClient implements AutoCloseable {
    private final AjusteeClientOptions options;
    private Vertx vertx;
    private WebClient webClient;

    public AjusteeClient(AjusteeClientOptions options) {
        this.options = Utils.requireNonNull(options);
    }

    private void ensureWebClient() {
        if (this.vertx != null) return;

        this.vertx = Vertx.vertx();

        final var webClientOptions = new WebClientOptions()
            .setKeepAlive(false);
        this.webClient = WebClient.create(vertx, webClientOptions); 
    }

    public void close() {
        if (this.vertx == null) return;

        this.webClient.close();
        this.webClient = null;
        this.vertx.close();
        this.vertx = null;
    }

    public Future<GetConfigurationResponse> getConfiguration(GetConfigurationRequest request) {
        Utils.requireNonNull(request);
        this.ensureWebClient();

        var comletableFuture = new CompletableFuture<GetConfigurationResponse>();

        final var webRequest = this.webClient.getAbs(this.options.getApiUri() + "/config")
            .as(BodyCodec.json(ConfigKey[].class));
        webRequest.putHeader("x-api-key", "L4W2umiK2lofMynXjThJDqseMXoCmg0f");
        request.getQueryParams().forEach((key, value) -> webRequest.addQueryParam(key, value));
        webRequest.send(webResponse -> {
            if (webResponse.succeeded()) {
                final var result = webResponse.result();
                if (result.statusCode() == 200) {
                    comletableFuture.complete(new GetConfigurationResponse(result.body()));
                } else {
                    comletableFuture.complete(new GetConfigurationResponse(result.statusCode()));
                }
            } else {
                comletableFuture.completeExceptionally(webResponse.cause());
            }
        });

        return comletableFuture;
    }

    public Future<UpdateConfigurationResponse> updateConfiguration(UpdateConfigurationRequest request) {
        Utils.requireNonNull(request);
        this.ensureWebClient();

        var comletableFuture = new CompletableFuture<UpdateConfigurationResponse>();

        final var webRequest = this.webClient.putAbs(this.options.getApiUri() + "/config/update/" + request.getPath());
        webRequest.putHeader("x-api-key", "L4W2umiK2lofMynXjThJDqseMXoCmg0f");
        webRequest.sendJsonObject(new JsonObject().put("value", request.getValue()), webResponse -> {
            if (webResponse.succeeded()) {
                final var result = webResponse.result();
                comletableFuture.complete(new UpdateConfigurationResponse(result.statusCode()));
            } else {
                comletableFuture.completeExceptionally(webResponse.cause());
            }
        });

        return comletableFuture;
    }
}
