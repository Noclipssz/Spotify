package com.radiospotify.DTOs;

public class UsuarioPlaylistResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String capaUrl;

    // Construtor
    public UsuarioPlaylistResponseDTO(Long id, String nome, String descricao, String capaUrl) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.capaUrl = capaUrl;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCapaUrl() {
        return capaUrl;
    }
}