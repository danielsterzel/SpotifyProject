package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SpotifyWebClientConfig {

    @Bean
    WebClient spotifyWebClient(WebClient.Builder builder){
        return builder
                .baseUrl("https://api.spotify.com/v1")
                .build();
    }
}
