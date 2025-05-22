package com.radiospotify.repository;
import com.radiospotify.model.Radio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RadioRepository extends JpaRepository<Radio, Long> {
    List<Radio> findByNomeContainingIgnoreCase(String nome);
}