package com.radiospotify.repository;

import com.radiospotify.model.UsuarioPlaylistFavorita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioPlaylistFavoritaRepository extends JpaRepository<UsuarioPlaylistFavorita, Long> {

    @Query("SELECT upf.playlistId FROM UsuarioPlaylistFavorita upf WHERE upf.usuarioId = :usuarioId")
    List<Long> findPlaylistIdsByUsuarioId(@Param("usuarioId") Long usuarioId);

    // Alternativa usando método derivado
    List<UsuarioPlaylistFavorita> findByUsuarioId(Long usuarioId);
}
