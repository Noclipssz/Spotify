package com.radiospotify.dto;

import jakarta.validation.constraints.*;

public class CreateUsuarioPlaylistDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    private String capaUrl;

    // Constructors
    public CreateUsuarioPlaylistDTO() {}

    public CreateUsuarioPlaylistDTO(String nome, String descricao, String capaUrl) {
        this.nome = nome;
        this.descricao = descricao;
        this.capaUrl = capaUrl;
    }

    // Getters and Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }
}

