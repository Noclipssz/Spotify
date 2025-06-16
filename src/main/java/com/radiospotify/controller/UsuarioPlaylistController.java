package com.radiospotify.controller;

import com.radiospotify.dto.*;
import com.radiospotify.service.UsuarioPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioPlaylistController {

    @Autowired
    private UsuarioPlaylistService usuarioPlaylistService;

    // POST /api/usuario-playlists/{usuarioId} - Criar playlist
    @PostMapping("/usuario-playlists/{usuarioId}")
    public ResponseEntity<UsuarioPlaylistResponseDTO> criarPlaylist(
            @PathVariable Long usuarioId,
            @Valid @RequestBody CreateUsuarioPlaylistDTO dto) {

        UsuarioPlaylistResponseDTO response = usuarioPlaylistService.criarPlaylist(usuarioId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET /api/usuario-playlists/{usuarioId} - Listar playlists do usuário
    @GetMapping("/usuario-playlists/{usuarioId}")
    public ResponseEntity<List<UsuarioPlaylistResponseDTO>> listarPlaylistsDoUsuario(
            @PathVariable Long usuarioId) {

        List<UsuarioPlaylistResponseDTO> playlists = usuarioPlaylistService.listarPlaylistsDoUsuario(usuarioId);
        return ResponseEntity.ok(playlists);
    }

    // POST /api/usuario-playlist-favoritas/{usuarioId} - Favoritar playlist
    @PostMapping("/usuario-playlist-favoritas/{usuarioId}")
    public ResponseEntity<FavoritaResponseDTO> favoritarPlaylist(
            @PathVariable Long usuarioId,
            @Valid @RequestBody FavoritarPlaylistDTO dto) {

        FavoritaResponseDTO response = usuarioPlaylistService.favoritarPlaylist(usuarioId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET /api/usuario-playlist-favoritas/{usuarioId} - Listar favoritas do usuário
    @GetMapping("/usuario-playlist-favoritas/{usuarioId}")
    public ResponseEntity<List<PlaylistIdDTO>> listarFavoritasDoUsuario(
            @PathVariable Long usuarioId) {

        List<PlaylistIdDTO> favoritas = usuarioPlaylistService.listarFavoritasDoUsuario(usuarioId);
        return ResponseEntity.ok(favoritas);
    }
}
