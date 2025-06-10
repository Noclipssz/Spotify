package com.radiospotify.repository;

import com.radiospotify.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    // Método básico para buscar músicas por playlist
    List<Musica> findByPlaylistId(Long playlistId);

    // Método para buscar músicas por playlist E tipo específico
    List<Musica> findByPlaylistIdAndTipo(Long playlistId, String tipo);

    // Método para buscar apenas por tipo
    List<Musica> findByTipo(String tipo);

    // Busca por título ou artista (consulta customizada mais robusta)
    @Query("SELECT m FROM Musica m WHERE LOWER(m.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(m.artista) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Musica> findByTituloOrArtistaContaining(@Param("termo") String termo);

    // Busca por título/artista E tipo específico
    @Query("SELECT m FROM Musica m WHERE (LOWER(m.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(m.artista) LIKE LOWER(CONCAT('%', :termo, '%'))) AND m.tipo = :tipo")
    List<Musica> findByTituloOrArtistaContainingAndTipo(@Param("termo") String termo, @Param("tipo") String tipo);
}