package com.radiospotify.controller;

import com.radiospotify.DTOs.MusicaDTO;
import com.radiospotify.DTOs.PlaylistDTO;
import com.radiospotify.DTOs.RadioDTO;
import com.radiospotify.DTOs.SearchResultDTO;
import com.radiospotify.model.Musica;
import com.radiospotify.model.Playlist;
import com.radiospotify.model.Radio;
import com.radiospotify.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private PlaylistRepository  playlistRepository;

    @Autowired
    private RadioRepository radioRepository;

    @GetMapping
    public ResponseEntity<SearchResultDTO> search(
            @RequestParam String termo,
            @RequestParam(required = false, defaultValue = "all") String tipo) {

        String termoLower = termo.toLowerCase();

        SearchResultDTO result = new SearchResultDTO();

        if (tipo.equals("all") || tipo.equals("musica")) {
            result.setMusicas(musicaRepository.findByTituloOrArtistaContaining(termoLower)
                    .stream()
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

    private MusicaDTO convertToMusicaDTO(Musica musica) {
        MusicaDTO dto = new MusicaDTO();
        dto.setId(musica.getId());
        dto.setTitulo(musica.getTitulo());
        dto.setArtista(musica.getArtista());
        dto.setDuracaoSegundos(musica.getDuracaoSegundos());
        dto.setUrlStream(musica.getUrlStream());
        dto.setCapaUrl(musica.getCapaUrl());
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