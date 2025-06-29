package com.radiospotify.controller;

import com.radiospotify.dto.UserResponseDTO;
import com.radiospotify.model.User;
import com.radiospotify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*") // Configure conforme sua necessidade de CORS
public class UserInfoController {

    @Autowired
    private UserService userService;

    /**
     * Atualiza o nome do usuário
     * @param userId ID do usuário (pode vir do token JWT ou sessão)
     * @param request Dados da requisição contendo o novo nome
     * @return ResponseEntity com os dados atualizados
     */
    @PutMapping("/atualizar-nome/{userId}")
    public ResponseEntity<Map<String, Object>> atualizarNome(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request) {

        Map<String, Object> response = new HashMap<>();

        try {
            String novoNome = request.get("nome");

            if (novoNome == null || novoNome.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Nome é obrigatório");
                return ResponseEntity.badRequest().body(response);
            }

            User userAtualizado = userService.atualizarNome(userId, novoNome);

            response.put("success", true);
            response.put("message", "Nome atualizado com sucesso");
            response.put("nome", userAtualizado.getNome());
            response.put("email", userAtualizado.getEmail());
            response.put("id", userAtualizado.getId());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", "Usuário não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Alternativa: Atualizar nome usando email (se você não tiver o ID disponível no frontend)
     * @param request Dados da requisição contendo email e novo nome
     * @return ResponseEntity com os dados atualizados
     */
    @PutMapping("/atualizar-nome-por-email")
    public ResponseEntity<Map<String, Object>> atualizarNomePorEmail(
            @RequestBody Map<String, String> request) {

        Map<String, Object> response = new HashMap<>();

        try {
            String email = request.get("email");
            String novoNome = request.get("nome");

            if (email == null || email.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Email é obrigatório");
                return ResponseEntity.badRequest().body(response);
            }

            if (novoNome == null || novoNome.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Nome é obrigatório");
                return ResponseEntity.badRequest().body(response);
            }

            // Usa o método do service que já faz toda a validação
            UserResponseDTO userAtualizado = userService.atualizarNomePorEmail(email, novoNome);

            response.put("success", true);
            response.put("message", "Nome atualizado com sucesso");
            response.put("nome", userAtualizado.getNome());
            response.put("email", userAtualizado.getEmail());
            response.put("id", userAtualizado.getId());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Busca dados do usuário por ID
     * @param userId ID do usuário
     * @return ResponseEntity com os dados do usuário
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> buscarUsuario(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<UserResponseDTO> userOptional = userService.buscarUsuarioDTO(userId);

            if (userOptional.isEmpty()) {
                response.put("success", false);
                response.put("message", "Usuário não encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            UserResponseDTO user = userOptional.get();
            response.put("success", true);
            response.put("id", user.getId());
            response.put("nome", user.getNome());
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro interno do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
