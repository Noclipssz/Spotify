package com.radiospotify.controller;

import com.radiospotify.repository.MusicaRepository;
import com.radiospotify.repository.PlaylistRepository;
import com.radiospotify.repository.RadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin("*")

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private RadioRepository radioRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam String termo,
            @RequestParam(required = false) String tipo) {

        String searchTerm = "%" + termo.toLowerCase() + "%";

        Map<String, Object> results = new HashMap<>();

        if (tipo == null || tipo.equals("radio")) {
            results.put("radios", radioRepository.findByNomeContainingIgnoreCase(termo));
        }

        if (tipo == null || tipo.equals("playlist")) {
            results.put("playlists", playlistRepository.findByNomeContainingIgnoreCase(termo));
        }

        if (tipo == null || tipo.equals("musica")) {
            results.put("musicas", musicaRepository.findByTituloContainingIgnoreCase(termo));
        }

        if (tipo == null || tipo.equals("autor")) {
            results.put("autores", musicaRepository.findByArtistaContainingIgnoreCase(termo));
        }

        return ResponseEntity.ok(results);
    }
}
