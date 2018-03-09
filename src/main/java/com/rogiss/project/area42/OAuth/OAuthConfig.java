package com.rogiss.project.area42.OAuth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthConfig {



    @Bean(name = "dropboxServiceProvider")
    OAuthServiceProvider getDropBoxServiceProvider(){
        return  new OAuthServiceProvider(getDropBoxConfig());
    }

    @Bean(name = "dropboxServiceConfig")
    OAuthServiceConfig getDropBoxConfig(){
        return new OAuthServiceConfig("zf9qe9fxakh07lp", "8m1sxep36lnk6or", "http://localhost:3000/connect/dropbox/callback", DropBoxApi20.class);
    }
}
