package com.radiospotify.repository;

import com.radiospotify.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MusicaRepository extends JpaRepository<Musica, Long> {
    List<Musica> findByPlaylistId(Long playlistId);
    //
    @Query("SELECT m FROM Musica m WHERE " +
            "LOWER(m.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(m.artista) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Musica> findByTituloOrArtistaContaining(@Param("termo") String termo);}