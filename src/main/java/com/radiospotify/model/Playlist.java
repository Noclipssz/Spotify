package com.radiospotify.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String capaUrl;

    @ManyToOne
    @JoinColumn(name = "radio_id")
    private Radio radio;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<Musica> musicas = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }
    public Radio getRadio() { return radio; }
    public void setRadio(Radio radio) { this.radio = radio; }
    public List<Musica> getMusicas() { return musicas; }
    public void setMusicas(List<Musica> musicas) { this.musicas = musicas; }
}

