package br.com.forumhub.forumhub.controller;

import br.com.forumhub.forumhub.config.JwtUtil;
import br.com.forumhub.forumhub.model.Usuario;
import br.com.forumhub.forumhub.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("email"), request.get("senha"))
        );

        String token = jwtUtil.generateToken(request.get("email"));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        if (usuarioRepository.findByEmail(request.get("email")).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado!");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.get("nome"));
        usuario.setEmail(request.get("email"));
        usuario.setSenha(passwordEncoder.encode(request.get("senha")));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}

