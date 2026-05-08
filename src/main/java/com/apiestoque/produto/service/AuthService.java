package com.apiestoque.produto.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

import com.apiestoque.produto.entity.Role;
import com.apiestoque.produto.entity.Usuario;
import com.apiestoque.produto.repository.UsuarioRepository;
import com.apiestoque.produto.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final JwtService jwtService;

    // ✅ Encoder (em produção, pode virar Bean)
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // =========================
    // LOGIN
    // =========================
    public String login(String username, String password) {

        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Usuário inválido"
                        )
                );

        if (!encoder.matches(password, usuario.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Senha inválida"
            );
        }

        return jwtService.gerarToken(
                usuario.getUsername(),
                usuario.getRole().name()
        );
    }

    // =========================
    // CADASTRO
    // =========================
    public void register(String username, String password) {

        // ✅ Verifica se usuário já existe
        if (repository.findByUsername(username).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Usuário já existe"
            );
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(encoder.encode(password)); // ✅ senha criptografada
        usuario.setRole(Role.ROLE_USER);                // ✅ role padrão

        repository.save(usuario);
    }
}
