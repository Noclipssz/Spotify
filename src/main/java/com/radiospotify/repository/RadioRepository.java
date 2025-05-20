package com.radiospotify.repository;

import com.radiospotify.model.Radio;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RadioRepository extends JpaRepository<Radio, Long> {

    @Query("SELECT r FROM Radio r WHERE LOWER(r.nome) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Radio> findByNomeContainingIgnoreCase(@Param("termo") String termo);

    @Query("SELECT r FROM Radio r WHERE LOWER(r.nome) LIKE LOWER(CONCAT('%', :termo, '%')) ")
    List<Radio> searchRadios(@Param("termo") String termo);
}