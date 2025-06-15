package com.radiospotify.dto;

public class UsuarioPlaylistResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String capaUrl;
    private Long usuarioId;

    // Constructors
    public UsuarioPlaylistResponseDTO() {}

    public UsuarioPlaylistResponseDTO(Long id, String nome, String descricao, String capaUrl, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.capaUrl = capaUrl;
        this.usuarioId = usuarioId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}

