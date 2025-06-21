package com.radiospotify.controller;

import com.radiospotify.dto.*;
import com.radiospotify.service.UsuarioPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UsuarioPlaylistController {

    @Autowired
    private UsuarioPlaylistService usuarioPlaylistService;

    // POST /api/usuario-playlists/{usuarioId} - Criar playlist (MANTIDO IGUAL)
    @PostMapping("/usuario-playlists/{usuarioId}")
    public ResponseEntity<UsuarioPlaylistResponseDTO> criarPlaylist(
            @PathVariable Long usuarioId,
            @Valid @RequestBody CreateUsuarioPlaylistDTO dto) {

        UsuarioPlaylistResponseDTO response = usuarioPlaylistService.criarPlaylist(usuarioId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET /api/usuario-playlists/{usuarioId} - Listar playlists do usuário (MANTIDO IGUAL)
    @GetMapping("/usuario-playlists/{usuarioId}")
    public ResponseEntity<List<UsuarioPlaylistResponseDTO>> listarPlaylistsDoUsuario(
            @PathVariable Long usuarioId) {

        List<UsuarioPlaylistResponseDTO> playlists = usuarioPlaylistService.listarPlaylistsDoUsuario(usuarioId);
        return ResponseEntity.ok(playlists);
    }

    // POST /api/usuario-playlist-favoritas/{usuarioId} - Favoritar playlist (MANTIDO IGUAL)
    @PostMapping("/usuario-playlist-favoritas/{usuarioId}")
    public ResponseEntity<FavoritaResponseDTO> favoritarPlaylist(
            @PathVariable Long usuarioId,
            @Valid @RequestBody FavoritarPlaylistDTO dto) {

        FavoritaResponseDTO response = usuarioPlaylistService.favoritarPlaylist(usuarioId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // DELETE /api/usuario-playlist-favoritas/{usuarioId}/{playlistId} - NOVO ENDPOINT
    @DeleteMapping("/usuario-playlist-favoritas/{usuarioId}/{playlistId}")
    public ResponseEntity<Map<String, Object>> desfavoritarPlaylist(
            @PathVariable Long usuarioId,
            @PathVariable Long playlistId) {

        usuarioPlaylistService.desfavoritarPlaylist(usuarioId, playlistId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Playlist removida dos favoritos");

        return ResponseEntity.ok(response);
    }

    // GET /api/usuario-playlist-favoritas/{usuarioId} - MÉTODO ALTERADO
    @GetMapping("/usuario-playlist-favoritas/{usuarioId}")
    public ResponseEntity<List<PlaylistFavoritaSemUsuarioDTO>> listarFavoritasDoUsuario(
            @PathVariable Long usuarioId) {

        List<PlaylistFavoritaSemUsuarioDTO> favoritas = usuarioPlaylistService.listarFavoritasDoUsuarioCorrigido(usuarioId);
        return ResponseEntity.ok(favoritas);
    }
}
