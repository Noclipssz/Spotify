package com.radiospotify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_playlist_favorita")
public class UsuarioPlaylistFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlist_id")
    private Long playlistId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    // Construtores
    public UsuarioPlaylistFavorita() {}

    public UsuarioPlaylistFavorita(Long playlistId, Long usuarioId) {
        this.playlistId = playlistId;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}