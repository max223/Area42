package com.rogiss.project.area42.OAuth;


import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class OAuthServiceProvider {

    private OAuthServiceConfig config;

    public OAuthServiceProvider() {
    }

    public OAuthServiceProvider(OAuthServiceConfig config) {
        this.config = config;
    }

    public OAuthService getService() {
        System.out.println(config);
        return new ServiceBuilder().provider(config.getApiClass())
                .apiKey(config.getApiKey())
                .apiSecret(config.getApiSecret())
                .callback(config.getCallback())
                .build();
    }

    public OAuthServiceConfig getConfig() {
        return config;
    }

    public void setConfig(OAuthServiceConfig config) {
        this.config = config;
    }
}
