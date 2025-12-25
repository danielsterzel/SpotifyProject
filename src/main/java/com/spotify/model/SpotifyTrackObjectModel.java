package com.spotify.model;

import java.util.List;

public record SpotifyTrackObjectModel(List<String> artistNames,
                                      Integer durationInMs,
                                      boolean trackHasExplicitLyrics,
                                      String spotifyTrackUrl,
                                      String spotifyWebApiEndpoint,
                                      String spotifyTrackId,
                                      String trackName,
                                      String spotifyTrackUri,
                                      boolean isLocalFile) {
}
