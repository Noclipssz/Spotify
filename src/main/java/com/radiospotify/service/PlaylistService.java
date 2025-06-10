package com.radiospotify.service;

import com.radiospotify.DTOs.PlaylistsFavoritasResponse;
import com.radiospotify.repository.UsuarioPlaylistFavoritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private UsuarioPlaylistFavoritaRepository repository;

    public List<Long> getPlaylistsFavoritasByUsuario(Long usuarioId) {
        return repository.findPlaylistIdsByUsuarioId(usuarioId);
    }

    public PlaylistsFavoritasResponse getPlaylistsFavoritasResponse(Long usuarioId) {
        List<Long> playlistIds = repository.findPlaylistIdsByUsuarioId(usuarioId);
        return new PlaylistsFavoritasResponse(usuarioId, playlistIds);
    }
}