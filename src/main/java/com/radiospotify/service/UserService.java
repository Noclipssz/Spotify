package com.radiospotify.service;

import com.radiospotify.dto.UserCreateDTO;
import com.radiospotify.dto.UserLoginDTO;
import com.radiospotify.dto.UserResponseDTO;
import com.radiospotify.model.User;
import com.radiospotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Cria um novo usuário
     * @param userCreateDTO Dados para criação do usuário
     * @return UserResponseDTO com os dados do usuário criado
     */
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

    /**
     * Autentica um usuário
     * @param loginDTO Dados de login
     * @return UserResponseDTO com os dados do usuário autenticado
     */
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

    /**
     * Atualiza o nome do usuário
     * @param userId ID do usuário
     * @param novoNome Novo nome do usuário
     * @return Usuario atualizado
     * @throws RuntimeException se o usuário não for encontrado
     */
    @Transactional
    public User atualizarNome(Long userId, String novoNome) {
        // Validação básica
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if (novoNome.trim().length() < 2 || novoNome.trim().length() > 100) {
            throw new IllegalArgumentException("Nome deve ter entre 2 e 100 caracteres");
        }

        // Busca o usuário
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User user = userOptional.get();
        user.setNome(novoNome.trim());

        return userRepository.save(user);
    }

    /**
     * Atualiza o nome do usuário usando email como identificador
     * @param email Email do usuário
     * @param novoNome Novo nome do usuário
     * @return UserResponseDTO com os dados atualizados
     */
    @Transactional
    public UserResponseDTO atualizarNomePorEmail(String email, String novoNome) {
        // Validação básica
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if (novoNome.trim().length() < 2 || novoNome.trim().length() > 100) {
            throw new IllegalArgumentException("Nome deve ter entre 2 e 100 caracteres");
        }

        // Busca o usuário por email
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User user = userOptional.get();
        user.setNome(novoNome.trim());

        User userAtualizado = userRepository.save(user);

        return new UserResponseDTO(
                userAtualizado.getId(),
                userAtualizado.getNome(),
                userAtualizado.getEmail()
        );
    }

    /**
     * Busca usuário por email
     * @param email Email do usuário
     * @return Usuario encontrado
     */
    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Busca usuário por ID
     * @param id ID do usuário
     * @return Usuario encontrado
     */
    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Busca usuário por ID e retorna DTO
     * @param id ID do usuário
     * @return UserResponseDTO com os dados do usuário
     */
    public Optional<UserResponseDTO> buscarUsuarioDTO(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Optional.of(new UserResponseDTO(
                    user.getId(),
                    user.getNome(),
                    user.getEmail()
            ));
        }

        return Optional.empty();
    }
}