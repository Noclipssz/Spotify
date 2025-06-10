// src/main/java/com/radiospotify/repository/UsuarioPlaylistRepository.java
package com.radiospotify.repository;

import com.radiospotify.model.UsuarioPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UsuarioPlaylistRepository extends JpaRepository<UsuarioPlaylist, Long> {

    @Query(value = "SELECT * FROM usuario_playlist WHERE usuario_id = :usuarioId", nativeQuery = true)
    List<Map<String, Object>> findPlaylistsByUsuarioId(@Param("usuarioId") Long usuarioId);
}