package com.api;

import com.spotify.client.SpotifyApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player") // prefix for all mappings in controller
public class PlayerController {
    private final SpotifyApiClient spotifyApiClient;

    public PlayerController(SpotifyApiClient spotifyApiClient){
        this.spotifyApiClient = spotifyApiClient;
    }
    @GetMapping("/raw")
    public String rawPlayerJson(){
        return spotifyApiClient.getCurrentPlayerRawJson();
    }
}
