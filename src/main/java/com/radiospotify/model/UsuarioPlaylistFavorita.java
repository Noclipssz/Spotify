package com.radiospotify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_playlist_favorita", schema = "radio_spotify")
@IdClass(UsuarioPlaylistFavoritaId.class)
public class UsuarioPlaylistFavorita {

    @Id
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Id
    @Column(name = "playlist_id")
    private Long playlistId;

    // Constructors
    public UsuarioPlaylistFavorita() {}

    public UsuarioPlaylistFavorita(Long usuarioId, Long playlistId) {
        this.usuarioId = usuarioId;
        this.playlistId = playlistId;
    }

    // Getters and Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
}
