package com.radiospotify.service;

import com.radiospotify.dto.*;
import com.radiospotify.model.UsuarioPlaylist;
import com.radiospotify.model.UsuarioPlaylistFavorita;
import com.radiospotify.repository.UsuarioPlaylistRepository;
import com.radiospotify.repository.UsuarioPlaylistFavoritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioPlaylistService {

    @Autowired
    private UsuarioPlaylistRepository usuarioPlaylistRepository;

    @Autowired
    private UsuarioPlaylistFavoritaRepository favoritaRepository;

    // Criar playlist
    public UsuarioPlaylistResponseDTO criarPlaylist(Long usuarioId, CreateUsuarioPlaylistDTO dto) {
        UsuarioPlaylist playlist = new UsuarioPlaylist(
                dto.getNome(),
                dto.getDescricao(),
                dto.getCapaUrl(),
                usuarioId
        );

        UsuarioPlaylist savedPlaylist = usuarioPlaylistRepository.save(playlist);

        return new UsuarioPlaylistResponseDTO(
                savedPlaylist.getId(),
                savedPlaylist.getNome(),
                savedPlaylist.getDescricao(),
                savedPlaylist.getCapaUrl(),
                savedPlaylist.getUsuarioId()
        );
    }

    // Listar playlists do usuário
    public List<UsuarioPlaylistResponseDTO> listarPlaylistsDoUsuario(Long usuarioId) {
        List<UsuarioPlaylist> playlists = usuarioPlaylistRepository.findByUsuarioId(usuarioId);

        return playlists.stream()
                .map(playlist -> new UsuarioPlaylistResponseDTO(
                        playlist.getId(),
                        playlist.getNome(),
                        playlist.getDescricao(),
                        playlist.getCapaUrl(),
                        playlist.getUsuarioId()
                ))
                .collect(Collectors.toList());
    }

    // Favoritar playlist
    public FavoritaResponseDTO favoritarPlaylist(Long usuarioId, FavoritarPlaylistDTO dto) {
        // Verificar se a playlist existe
        if (!usuarioPlaylistRepository.existsById(dto.getPlaylistId())) {
            throw new RuntimeException("Playlist não encontrada");
        }

        UsuarioPlaylistFavorita favorita = new UsuarioPlaylistFavorita(usuarioId, dto.getPlaylistId());
        favoritaRepository.save(favorita);

        return new FavoritaResponseDTO(usuarioId, dto.getPlaylistId());
    }

    // Listar favoritas do usuário
    public List<PlaylistIdDTO> listarFavoritasDoUsuario(Long usuarioId) {
        List<UsuarioPlaylistFavorita> favoritas = favoritaRepository.findByUsuarioId(usuarioId);

        return favoritas.stream()
                .map(favorita -> new PlaylistIdDTO(favorita.getPlaylistId()))
                .collect(Collectors.toList());
    }
}

