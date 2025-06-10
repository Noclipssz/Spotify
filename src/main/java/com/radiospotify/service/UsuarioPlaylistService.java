// src/main/java/com/radiospotify/service/UsuarioPlaylistService.java
package com.radiospotify.service;

import com.radiospotify.DTOs.UsuarioPlaylistRequestDTO;
import com.radiospotify.DTOs.UsuarioPlaylistResponseDTO;
import com.radiospotify.model.UsuarioPlaylist;
import com.radiospotify.model.User;
import com.radiospotify.repository.UsuarioPlaylistRepository;
import com.radiospotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioPlaylistService {

    @Autowired
    private UsuarioPlaylistRepository repository;

    public List<Map<String, Object>> listarPlaylistsCru(Long usuarioId) {
        return repository.findPlaylistsByUsuarioId(usuarioId);
    }
}