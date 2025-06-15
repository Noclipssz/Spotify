package com.radiospotify.dto;

public class PlaylistDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String capaUrl;

    public PlaylistDTO(Long id, String nome, String descricao, String capaUrl) {
    }

    public PlaylistDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapaUrl() {
        return capaUrl;
    }

    public void setCapaUrl(String capaUrl) {
        this.capaUrl = capaUrl;
    }
}
