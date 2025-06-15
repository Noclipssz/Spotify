package com.radiospotify.model;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioPlaylistFavoritaId implements Serializable {
    private Long usuarioId;
    private Long playlistId;

    public UsuarioPlaylistFavoritaId() {}

    public UsuarioPlaylistFavoritaId(Long usuarioId, Long playlistId) {
        this.usuarioId = usuarioId;
        this.playlistId = playlistId;
    }

    // Getters, Setters, equals, hashCode
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPlaylistFavoritaId that = (UsuarioPlaylistFavoritaId) o;
        return Objects.equals(usuarioId, that.usuarioId) && Objects.equals(playlistId, that.playlistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, playlistId);
    }
}
