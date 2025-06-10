// src/main/java/com/radiospotify/model/UsuarioPlaylistFavoritaId.java
package com.radiospotify.model;

import java.io.Serializable;

public class UsuarioPlaylistFavoritaId implements Serializable {
    private Long usuario;
    private Long playlist;

    // Getters e Setters
    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Long playlist) {
        this.playlist = playlist;
    }
}