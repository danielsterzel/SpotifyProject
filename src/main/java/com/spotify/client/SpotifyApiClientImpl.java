package com.spotify.client;

import com.security.AuthFacade;
import com.spotify.model.PlayerState.PlayerState;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 1. WebClient -> builds and sends Http Request
 * 2. Mono<T> -> promise that I will receive one object of type T
 * 3. bodyToMono(...class) can be used to map object to any class. It map Json fields to class fields.
 * */

@Component
public class SpotifyApiClientImpl  implements SpotifyApiClient{

    private final AuthFacade auth;
    private final WebClient webClient;

    public SpotifyApiClientImpl(AuthFacade auth, WebClient webClient){
        this.auth = auth;
        this.webClient = webClient; // spring uses the SpotifyWebClientConfig for creating WebClient type object
    }

    @Override
    public PlayerState getCurrentPlayer(){
        return new PlayerState("computer");
    }
    @Override
    public void enqueueTrack(String trackUri)
    {}
    @Override
    public void skipToNext()
    {}
    @Override
    public String getCurrentPlayerRawJson() throws RuntimeException
    {
        var spotifyAccessToken = auth.getAccessTokenValue();

        var oauth = auth.getOauth2AuthenticationToken();
        System.out.println(oauth.getAuthorities());

        return webClient
                .get()
                .uri("/me/player")
                .header("Authorization", "Bearer " + spotifyAccessToken)
                .retrieve()// execute
                .onStatus(status -> status.value() == 401,
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Spotify 401" + body)))
                .bodyToMono(String.class)
                .block(); // block thread and give me the result -> sync communication
    }
}
