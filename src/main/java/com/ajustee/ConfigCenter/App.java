package com.ajustee.ConfigCenter;

import java.util.concurrent.ExecutionException;
import java.time.LocalDateTime;

public class App {
    public static void main( String[] args ) {
        //System.out.println( "Hello World!" );
        
        final var options = new AjusteeClientOptions("https://ayylj6pb3c.execute-api.us-west-2.amazonaws.com/fo",
            "L4W2umiK2lofMynXjThJDqseMXoCmg0f");
        try(final var client = new AjusteeClient(options)) {
            System.out.println("Update Configuration");
            try {
                final var request = new UpdateConfigurationRequest("ns/keyInt1", LocalDateTime.now().getNano());
                final var future = client.updateConfiguration(request);
                final var response = future.get();
                
                System.out.println("Status code: " + response.getStatusCode());
                if (response.succeeded()) System.out.println("Result: ok");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e.getMessage());
            } catch(ExecutionException e) {
                System.out.println("ExecutionException: " + e.getMessage());
            }

            System.out.println();
            System.out.println("Get Configuration");
            try {
                final var request = new GetConfigurationRequest();
                request.setPath("ns/");
                final var future = client.getConfiguration(request);
                final var response = future.get();
                
                System.out.println("Status code: " + response.getStatusCode());
                if (response.succeeded()) {
                    System.out.println("Key count: " + response.getConfigKeys().length);
                    for (final ConfigKey key : response.getConfigKeys()) {
                        System.out.printf("path: %s; type: %s; value: %s", key.getPath(), key.getDataType(), key.getValue());
                        System.out.println();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e.getMessage());
            } catch(ExecutionException e) {
                System.out.println("ExecutionException: " + e.getMessage());
            }
        }
    }
}
