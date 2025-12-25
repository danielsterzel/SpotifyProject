package com.spotify.model.PlayerState;

import java.util.Optional;
import com.spotify.model.SpotifyTrackObjectModel;

public record PlayerState(String deviceType,
                          String repeatState,
                          boolean shuffleStateOn,
                          Integer timestamp,
                          Integer progressInMs,
                          boolean isPlaying,
                          Optional<SpotifyTrackObjectModel> track) {}
