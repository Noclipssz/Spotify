package com.radiospotify.dto;

public class PlaylistFavoritaSemUsuarioDTO {
    private Long playlistId;
    private String nome;
    private String descricao;
    private String capaUrl;

    // Constructors
    public PlaylistFavoritaSemUsuarioDTO() {}

    public PlaylistFavoritaSemUsuarioDTO(Long playlistId, String nome, String descricao, String capaUrl) {
        this.playlistId = playlistId;
        this.nome = nome;
        this.descricao = descricao;
        this.capaUrl = capaUrl;
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
}

