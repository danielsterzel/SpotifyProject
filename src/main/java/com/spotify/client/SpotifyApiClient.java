package com.spotify.client;

import com.spotify.model.PlayerState.PlayerState;

public interface SpotifyApiClient {
    PlayerState getCurrentPlayer();
    void enqueueTrack(String trackUri);
    void skipToNext();
    String getCurrentPlayerRawJson();
    
}
