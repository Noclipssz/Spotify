package com.radiospotify.dto;

public class FavoritaResponseDTO {
    private Long usuarioId;
    private Long playlistId;

    // Constructors
    public FavoritaResponseDTO() {}

    public FavoritaResponseDTO(Long usuarioId, Long playlistId) {
        this.usuarioId = usuarioId;
        this.playlistId = playlistId;
    }

    // Getters and Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
}

