package com.radiospotify.repository;

import com.radiospotify.model.UsuarioPlaylistFavorita;
import com.radiospotify.model.UsuarioPlaylistFavoritaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioPlaylistFavoritaRepository extends JpaRepository<UsuarioPlaylistFavorita, UsuarioPlaylistFavoritaId> {
    List<UsuarioPlaylistFavorita> findByUsuarioId(Long usuarioId);
}
