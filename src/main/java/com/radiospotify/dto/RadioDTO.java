package com.radiospotify.dto;

import java.util.List;

public class RadioDTO {
    private Long id;
    private String nome;
    private String capaUrl;
    private String tipo;
    private List<PlaylistDTO> playlists;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public List<PlaylistDTO> getPlaylists() { return playlists; }
    public void setPlaylists(List<PlaylistDTO> playlists) { this.playlists = playlists; }
}