package com.radiospotify.repository;

import com.radiospotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByRadioId(Long radioId);


        @Query("SELECT p FROM Playlist p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%'))")
        List<Playlist> findByNomeContainingIgnoreCase(@Param("termo") String termo);

        @Query("SELECT p FROM Playlist p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
                "LOWER(p.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))")
        List<Playlist> searchPlaylists(@Param("termo") String termo);

}