package com.radiospotify.service;

import com.radiospotify.DTOs.UserCreateDTO;
import com.radiospotify.DTOs.UserLoginDTO;
import com.radiospotify.DTOs.UserResponseDTO;
import com.radiospotify.model.User;
import com.radiospotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        // Verifica se email já existe
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }

        // Cria novo usuário
        User user = new User();
        user.setNome(userCreateDTO.getNome());
        user.setEmail(userCreateDTO.getEmail());
        user.setSenha(userCreateDTO.getSenha());

        // Salva no banco (a senha será criptografada pelo trigger/função)
        User savedUser = userRepository.save(user);

        // Retorna DTO sem a senha
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getNome(),
                savedUser.getEmail()
        );
    }

    public UserResponseDTO authenticateUser(UserLoginDTO loginDTO) {
        // Busca usuário com email e senha criptografada
        Optional<User> userOpt = userRepository.findByEmailAndPassword(
                loginDTO.getEmail(),
                loginDTO.getSenha()
        );

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        User user = userOpt.get();
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }
}
