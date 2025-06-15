package com.radiospotify.model;


import jakarta.persistence.*;



@Entity
@Table(name = "usuario_playlist", schema = "radio_spotify")
public class UsuarioPlaylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "capa_url")
    private String capaUrl;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    // Constructors
    public UsuarioPlaylist() {}

    public UsuarioPlaylist(String nome, String descricao, String capaUrl, Long usuarioId) {
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
