package com.radiospotify.dto;

public class PlaylistFavoritaDTO {
    private Long playlistId;
    private String nome;
    private String descricao;
    private String capaUrl;
    private Long usuarioId;

    // Constructors
    public PlaylistFavoritaDTO() {}

    public PlaylistFavoritaDTO(Long playlistId, String nome, String descricao, String capaUrl, Long usuarioId) {
        this.playlistId = playlistId;
        this.nome = nome;
        this.descricao = descricao;
        this.capaUrl = capaUrl;
        this.usuarioId = usuarioId;
    }

    // Getters and Setters
    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}

