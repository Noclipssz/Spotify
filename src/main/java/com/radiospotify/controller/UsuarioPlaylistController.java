// src/main/java/com/radiospotify/controller/UsuarioPlaylistController.java
package com.radiospotify.controller;

import com.radiospotify.DTOs.UsuarioPlaylistRequestDTO;
import com.radiospotify.DTOs.UsuarioPlaylistResponseDTO;
import com.radiospotify.service.UsuarioPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuario/{usuarioId}/playlists")
public class UsuarioPlaylistController {

    @Autowired
    private UsuarioPlaylistService service;

    @GetMapping("/playlists-usuario")
    public ResponseEntity<List<Map<String, Object>>> listarPlaylistsCru(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPlaylistsCru(usuarioId));
    }
}