package com.radiospotify.DTOs;

public class PlaylistFavoritaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String capaUrl;
    private Boolean favorita;

    // Default constructor (required by JPA)
    public PlaylistFavoritaDTO() {}

    // Constructor for JPQL queries
    public PlaylistFavoritaDTO(Long id, String nome, String descricao, String capaUrl, Boolean favorita) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.capaUrl = capaUrl;
        this.favorita = favorita;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }

    public Boolean getFavorita() { return favorita; }
    public void setFavorita(Boolean favorita) { this.favorita = favorita; }
}