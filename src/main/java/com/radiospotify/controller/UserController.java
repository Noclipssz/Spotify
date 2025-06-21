package com.radiospotify.controller;

import com.radiospotify.dto.UserCreateDTO;
import com.radiospotify.dto.UserLoginDTO;
import com.radiospotify.dto.UserResponseDTO;
import com.radiospotify.security.JwtUtil;
import com.radiospotify.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        try {
            UserResponseDTO user = userService.createUser(userCreateDTO);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Usuário criado com sucesso");
            response.put("user", user);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        try {
            UserResponseDTO user = userService.authenticateUser(loginDTO);

            // Gerar token JWT
            String token = jwtUtil.generateToken(user.getEmail(), user.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login realizado com sucesso");
            response.put("user", user);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // Endpoint protegido para testar
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            // Aqui você pode pegar o usuário autenticado do SecurityContext
            // String email = SecurityContextHolder.getContext().getAuthentication().getName();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Perfil acessado com sucesso");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro ao acessar perfil");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
