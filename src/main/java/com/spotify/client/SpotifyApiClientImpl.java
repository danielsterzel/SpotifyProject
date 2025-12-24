package com.spotify.client;

import com.security.AuthFacade;
import com.spotify.model.PlayerState;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SpotifyApiClientImpl  implements SpotifyApiClient{

    private final AuthFacade auth;
    private final WebClient webClient;

    public SpotifyApiClientImpl(AuthFacade auth, WebClient webClient){
        this.auth = auth;
        this.webClient = webClient;
    }

    @Override
    public PlayerState getCurrentPlayer(){
        return new PlayerState();
    }
    @Override
    public void enqueueTrack(String trackUri){}
    @Override
    public void skipToNext(){}
    @Override
    public String getCurrentPlayerRawJson(){}

}
