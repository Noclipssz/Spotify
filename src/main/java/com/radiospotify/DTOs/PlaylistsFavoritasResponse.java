package com.radiospotify.DTOs;

import java.util.List;

public class PlaylistsFavoritasResponse {
    private Long usuarioId;
    private List<Long> playlistIds;
    private int totalPlaylists;

    public PlaylistsFavoritasResponse(Long usuarioId, List<Long> playlistIds) {
        this.usuarioId = usuarioId;
        this.playlistIds = playlistIds;
        this.totalPlaylists = playlistIds.size();
    }

    // Getters e Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public List<Long> getPlaylistIds() { return playlistIds; }
    public void setPlaylistIds(List<Long> playlistIds) { this.playlistIds = playlistIds; }

    public int getTotalPlaylists() { return totalPlaylists; }
    public void setTotalPlaylists(int totalPlaylists) { this.totalPlaylists = totalPlaylists; }
}