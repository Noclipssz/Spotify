package com.radiospotify.repository;

import com.radiospotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByRadioId(Long radioId);
    List<Playlist> findByNomeContainingIgnoreCase(String nome);
    List<Playlist> findById(int id);
}