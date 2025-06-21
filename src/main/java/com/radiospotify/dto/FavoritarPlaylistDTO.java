package com.radiospotify.dto;

import jakarta.validation.constraints.NotNull;

public class FavoritarPlaylistDTO {

    @NotNull(message = "ID da playlist é obrigatório")
    private Long playlistId;

    // Constructors
    public FavoritarPlaylistDTO() {}

    public FavoritarPlaylistDTO(Long playlistId) {
        this.playlistId = playlistId;
    }

    // Getters and Setters
    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
}
