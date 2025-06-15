package com.radiospotify.repository;

import com.radiospotify.model.UsuarioPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioPlaylistRepository extends JpaRepository<UsuarioPlaylist, Long> {
    List<UsuarioPlaylist> findByUsuarioId(Long usuarioId);
}
