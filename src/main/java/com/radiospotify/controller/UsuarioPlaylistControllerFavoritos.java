package com.radiospotify.controller;

import com.radiospotify.DTOs.PlaylistDTO;
import com.radiospotify.DTOs.PlaylistsFavoritasResponse;
import com.radiospotify.model.Playlist;
import com.radiospotify.repository.PlaylistRepository;
import com.radiospotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioPlaylistControllerFavoritos {

    @Autowired
    private PlaylistService playlistService;

    // Opção 1: Retorna apenas lista de IDs
    @GetMapping("/{id}/playlists/playlists-favoritas")
    public ResponseEntity<List<Long>> getPlaylistsFavoritas(@PathVariable Long id) {
        try {
            List<Long> playlistIds = playlistService.getPlaylistsFavoritasByUsuario(id);

            if (playlistIds.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(playlistIds);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Opção 2: Retorna objeto estruturado com mais informações
    @GetMapping("/{id}/playlists/playlists-favoritas/detalhado")
    public ResponseEntity<PlaylistsFavoritasResponse> getPlaylistsFavoritasDetalhado(@PathVariable Long id) {
        try {
            PlaylistsFavoritasResponse response = playlistService.getPlaylistsFavoritasResponse(id);

            if (response.getPlaylistIds().isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}