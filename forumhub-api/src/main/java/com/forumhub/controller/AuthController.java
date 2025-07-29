package com.forumhub.controller;

import com.forumhub.dto.LoginDTO;
import com.forumhub.model.Usuario;
import com.forumhub.repository.UsuarioRepository;
import com.forumhub.security.JwtUtil;
import com.forumhub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@ResponseBody LoginDTO dados){
        Usuario usuario = usuarioRepo.findByEmail(dados.getEmail());
        if (usuario != null && usuario.getSenha()).equals(dados.getSenha())){
            String token = jwtUtil.gerarToken(usuario.getEmail));
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @PostMapping
    public ResponseEntity<String> autenticar(@RequestBody LoginDTO login){
        return usuarioService.autenticar(login);
    }
}
