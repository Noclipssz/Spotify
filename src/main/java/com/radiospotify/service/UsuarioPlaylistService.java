package com.radiospotify.service;

import com.radiospotify.dto.*;
import com.radiospotify.model.UsuarioPlaylist;
import com.radiospotify.model.UsuarioPlaylistFavorita;
import com.radiospotify.model.Playlist; // NOVO IMPORT
import com.radiospotify.model.UsuarioPlaylistFavoritaId;
import com.radiospotify.repository.UsuarioPlaylistRepository;
import com.radiospotify.repository.UsuarioPlaylistFavoritaRepository;
import com.radiospotify.repository.PlaylistRepository; // NOVO IMPORT
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

    @Autowired
    private PlaylistRepository playlistRepository; // NOVA INJEÇÃO

    // TODOS OS MÉTODOS EXISTENTES MANTIDOS IGUAIS...

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

    // Favoritar playlist - CORRIGIDO para verificar na tabela playlist
    public FavoritaResponseDTO favoritarPlaylist(Long usuarioId, FavoritarPlaylistDTO dto) {
        // Verificar se a playlist existe na tabela playlist
        if (!playlistRepository.existsById(dto.getPlaylistId())) {
            throw new RuntimeException("Playlist não encontrada");
        }

        // Verificar se já não está favoritada
        UsuarioPlaylistFavoritaId id = new UsuarioPlaylistFavoritaId(usuarioId, dto.getPlaylistId());
        if (favoritaRepository.existsById(id)) {
            throw new RuntimeException("Playlist já está favoritada");
        }

        UsuarioPlaylistFavorita favorita = new UsuarioPlaylistFavorita(usuarioId, dto.getPlaylistId());
        favoritaRepository.save(favorita);

        return new FavoritaResponseDTO(usuarioId, dto.getPlaylistId());
    }

    // NOVO MÉTODO - Desfavoritar playlist
    public void desfavoritarPlaylist(Long usuarioId, Long playlistId) {
        UsuarioPlaylistFavoritaId id = new UsuarioPlaylistFavoritaId(usuarioId, playlistId);

        if (!favoritaRepository.existsById(id)) {
            throw new RuntimeException("Playlist não está favoritada");
        }

        favoritaRepository.deleteById(id);
    }

    // MÉTODO ANTIGO MANTIDO (caso seja usado em outros lugares)
    public List<PlaylistFavoritaDTO> listarFavoritasDoUsuario(Long usuarioId) {
        List<UsuarioPlaylistFavorita> favoritas = favoritaRepository.findByUsuarioId(usuarioId);

        return favoritas.stream()
                .map(favorita -> {
                    // Buscar dados da playlist
                    UsuarioPlaylist playlist = usuarioPlaylistRepository.findById(favorita.getPlaylistId())
                            .orElse(null);

                    if (playlist != null) {
                        return new PlaylistFavoritaDTO(
                                playlist.getId(),
                                playlist.getNome(),
                                playlist.getDescricao(),
                                playlist.getCapaUrl(),
                                playlist.getUsuarioId()
                        );
                    }
                    return null;
                })
                .filter(dto -> dto != null) // Remove playlists que não existem mais
                .collect(Collectors.toList());
    }

    // NOVO MÉTODO que consulta a tabela playlist correta
    public List<PlaylistFavoritaSemUsuarioDTO> listarFavoritasDoUsuarioCorrigido(Long usuarioId) {
        List<UsuarioPlaylistFavorita> favoritas = favoritaRepository.findByUsuarioId(usuarioId);

        return favoritas.stream()
                .map(favorita -> {
                    // Buscar dados da playlist na tabela playlist
                    Playlist playlist = playlistRepository.findById(favorita.getPlaylistId())
                            .orElse(null);

                    if (playlist != null) {
                        return new PlaylistFavoritaSemUsuarioDTO(
                                playlist.getId(),
                                playlist.getNome(),
                                playlist.getDescricao(),
                                playlist.getCapaUrl()
                        );
                    }
                    return null;
                })
                .filter(dto -> dto != null) // Remove playlists que não existem mais
                .collect(Collectors.toList());
    }
}

