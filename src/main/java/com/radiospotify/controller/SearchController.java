package com.radiospotify.controller;

import com.radiospotify.dto.MusicaDTO;
import com.radiospotify.dto.PlaylistDTO;
import com.radiospotify.dto.RadioDTO;
import com.radiospotify.dto.SearchResultDTO;
import com.radiospotify.model.Musica;
import com.radiospotify.model.Playlist;
import com.radiospotify.model.Radio;
import com.radiospotify.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private RadioRepository radioRepository;

    @GetMapping("/search")
    public ResponseEntity<SearchResultDTO> search(
            @RequestParam String termo,
            @RequestParam(required = false, defaultValue = "all") String tipo,
            @RequestParam(required = false) String tipoMusica) {

        String termoLower = termo.toLowerCase();
        SearchResultDTO result = new SearchResultDTO();

        if (tipo.equals("all") || tipo.equals("musica")) {
            List<Musica> musicas;

            if (tipoMusica != null && !tipoMusica.isEmpty() && !tipoMusica.equals("all")) {
                // Buscar por termo E tipo específico
                musicas = musicaRepository.findByTituloOrArtistaContainingAndTipo(termoLower, tipoMusica);
            } else {
                // Buscar apenas por termo
                musicas = musicaRepository.findByTituloOrArtistaContaining(termoLower);
            }

            result.setMusicas(musicas.stream()
                    .map(this::convertToMusicaDTO)
                    .collect(Collectors.toList()));
        }

        if (tipo.equals("all") || tipo.equals("playlist")) {
            result.setPlaylists(playlistRepository.findByNomeContainingIgnoreCase(termoLower)
                    .stream()
                    .map(this::convertToPlaylistDTO)
                    .collect(Collectors.toList()));
        }

        if (tipo.equals("all") || tipo.equals("radio")) {
            result.setRadios(radioRepository.findByNomeContainingIgnoreCase(termoLower)
                    .stream()
                    .map(this::convertToRadioDTO)
                    .collect(Collectors.toList()));
        }

        return ResponseEntity.ok(result);
    }

    // Endpoint para buscar músicas por tipo (sem termo de busca)
    @GetMapping("/musicas/tipo")
    public ResponseEntity<List<MusicaDTO>> getMusicasByTipo(@RequestParam String tipo) {
        try {
            List<Musica> musicas;

            if (tipo.equals("all")) {
                musicas = musicaRepository.findAll();
            } else {
                musicas = musicaRepository.findByTipo(tipo);
            }

            List<MusicaDTO> musicaDTOs = musicas.stream()
                    .map(this::convertToMusicaDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(musicaDTOs);

        } catch (Exception e) {
            // Log do erro
            System.err.println("Erro ao buscar músicas por tipo " + tipo + ": " + e.getMessage());
            return ResponseEntity.ok(List.of()); // Retorna lista vazia em caso de erro
        }
    }

    // Endpoint para buscar músicas de uma playlist por tipo
    @GetMapping("/playlist/{playlistId}/musicas")
    public ResponseEntity<List<MusicaDTO>> getPlaylistMusicasByTipo(
            @PathVariable Long playlistId,
            @RequestParam(required = false, defaultValue = "all") String tipo) {

        try {
            List<Musica> musicas;

            if (tipo.equals("all")) {
                // Buscar todas as músicas da playlist
                musicas = musicaRepository.findByPlaylistId(playlistId);
            } else {
                // Buscar músicas da playlist filtradas por tipo
                musicas = musicaRepository.findByPlaylistIdAndTipo(playlistId, tipo);
            }

            List<MusicaDTO> musicaDTOs = musicas.stream()
                    .map(this::convertToMusicaDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(musicaDTOs);

        } catch (Exception e) {
            // Log do erro
            System.err.println("Erro ao buscar músicas da playlist " + playlistId + ": " + e.getMessage());
            return ResponseEntity.ok(List.of()); // Retorna lista vazia em caso de erro
        }
    }

    private MusicaDTO convertToMusicaDTO(Musica musica) {
        MusicaDTO dto = new MusicaDTO();
        dto.setId(musica.getId());
        dto.setTitulo(musica.getTitulo());
        dto.setArtista(musica.getArtista());
        dto.setDuracaoSegundos(musica.getDuracaoSegundos());
        dto.setUrlStream(musica.getUrlStream());
        dto.setCapaUrl(musica.getCapaUrl());
        dto.setTipo(musica.getTipo());
        return dto;
    }

    private PlaylistDTO convertToPlaylistDTO(Playlist playlist) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(playlist.getId());
        dto.setNome(playlist.getNome());
        dto.setDescricao(playlist.getDescricao());
        dto.setCapaUrl(playlist.getCapaUrl());
        return dto;
    }

    private RadioDTO convertToRadioDTO(Radio radio) {
        RadioDTO dto = new RadioDTO();
        dto.setId(radio.getId());
        dto.setNome(radio.getNome());
        dto.setCapaUrl(radio.getCapaUrl());
        return dto;
    }
}