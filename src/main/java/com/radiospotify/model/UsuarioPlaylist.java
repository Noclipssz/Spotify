// src/main/java/com/radiospotify/model/UsuarioPlaylist.java
package com.radiospotify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_playlist")
public class UsuarioPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String capaUrl;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    // Construtores
    public UsuarioPlaylist() {}

    public UsuarioPlaylist(String nome, String descricao, String capaUrl, User usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.capaUrl = capaUrl;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Long getId() {
        return id;
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

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}