package com.radiospotify.dto;

public class MusicaDTO {
    private Long id;
    private String titulo;
    private String artista;
    private Double duracaoSegundos;
    private String urlStream;
    private String capaUrl;
    private String tipo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public Double getDuracaoSegundos() { return duracaoSegundos; }
    public void setDuracaoSegundos(Double duracaoSegundos) { this.duracaoSegundos = duracaoSegundos; }
    public String getUrlStream() { return urlStream; }
    public void setUrlStream(String urlStream) { this.urlStream = urlStream; }
    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}