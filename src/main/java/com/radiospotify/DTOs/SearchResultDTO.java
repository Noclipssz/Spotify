package com.radiospotify.DTOs;

import java.util.List;

public class SearchResultDTO {
    private List<MusicaDTO> musicas;
    private List<PlaylistDTO> playlists;
    private List<RadioDTO> radios;

    public List<MusicaDTO> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaDTO> musicas) {
        this.musicas = musicas;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public List<RadioDTO> getRadios() {
        return radios;
    }

    public void setRadios(List<RadioDTO> radios) {
        this.radios = radios;
    }
// Getters e Setters
}