package com.radiospotify.dto;

public class PlaylistIdDTO {
    private Long playlistId;

    // Constructors
    public PlaylistIdDTO() {}

    public PlaylistIdDTO(Long playlistId) {
        this.playlistId = playlistId;
    }

    // Getters and Setters
    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
}

