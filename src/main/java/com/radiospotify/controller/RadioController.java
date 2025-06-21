package com.radiospotify.controller;

import com.radiospotify.dto.MusicaDTO;
import com.radiospotify.dto.PlaylistDTO;
import com.radiospotify.dto.RadioDTO;
import com.radiospotify.model.Musica;
import com.radiospotify.model.Playlist;
import com.radiospotify.model.Radio;
import com.radiospotify.repository.MusicaRepository;
import com.radiospotify.repository.PlaylistRepository;
import com.radiospotify.repository.RadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/radios")
public class RadioController {

    private final RadioRepository radioRepository;
    private final PlaylistRepository playlistRepository;
    private final MusicaRepository musicaRepository;

    @Autowired
    public RadioController(RadioRepository radioRepository,
                           PlaylistRepository playlistRepository,
                           MusicaRepository musicaRepository) {
        this.radioRepository = radioRepository;
        this.playlistRepository = playlistRepository;
        this.musicaRepository = musicaRepository;
    }

    @GetMapping
    public ResponseEntity<List<RadioDTO>> getAllRadios() {
        List<RadioDTO> radioDTOs = radioRepository.findAll().stream()
                .map(this::convertToRadioDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(radioDTOs);
    }

    @GetMapping("/grouped")
    public ResponseEntity<Map<String, List<RadioDTO>>> getRadiosGrouped() {
        List<Radio> radios = radioRepository.findAll();

        // Agrupa por tipo usando Stream API e converte para DTO
        Map<String, List<RadioDTO>> grouped = radios.stream()
                .collect(Collectors.groupingBy(radio ->
                                radio.getTipo() != null ? radio.getTipo() : "outros",
                        Collectors.mapping(this::convertToRadioDTO, Collectors.toList())
                ));

        return ResponseEntity.ok(grouped);
    }

    @GetMapping("/{radioId}/playlists")
    public ResponseEntity<List<PlaylistDTO>> getPlaylistsByRadio(@PathVariable Long radioId) {
        if (!radioRepository.existsById(radioId)) {
            return ResponseEntity.notFound().build();
        }

        List<PlaylistDTO> playlistDTOs = playlistRepository.findByRadioId(radioId).stream()
                .map(this::convertToPlaylistDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(playlistDTOs);
    }

    @GetMapping("/{radioId}/playlists/{playlistId}/musicas")
    public ResponseEntity<List<MusicaDTO>> getMusicasByPlaylist(
            @PathVariable Long radioId,
            @PathVariable Long playlistId) {

        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isEmpty() || !playlist.get().getRadio().getId().equals(radioId)) {
            return ResponseEntity.notFound().build();
        }

        List<MusicaDTO> musicaDTOs = musicaRepository.findByPlaylistId(playlistId).stream()
                .map(this::convertToMusicaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(musicaDTOs);
    }

    private RadioDTO convertToRadioDTO(Radio radio) {
        RadioDTO dto = new RadioDTO();
        dto.setId(radio.getId());
        dto.setNome(radio.getNome());
        dto.setCapaUrl(radio.getCapaUrl());

        // Define valor padrão "outros" se tipo for null
        dto.setTipo(radio.getTipo() != null ? radio.getTipo() : "outros");

        if (radio.getPlaylists() != null && !radio.getPlaylists().isEmpty()) {
            dto.setPlaylists(radio.getPlaylists().stream()
                    .map(this::convertToPlaylistDTO)
                    .collect(Collectors.toList()));
        }

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
}