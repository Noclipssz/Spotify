package com.radiospotify.repository;

import com.radiospotify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Verifica se email já existe
    boolean existsByEmail(String email);

    // Busca usuário por email
    Optional<User> findByEmail(String email);

    // Verificação de login com criptografia no banco
    @Query(value = "SELECT * FROM usuarios WHERE email = :email AND senha = SHA2(:senha, 256)",
            nativeQuery = true)
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("senha") String senha);
}

