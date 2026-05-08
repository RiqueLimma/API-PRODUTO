package com.apiestoque.produto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.apiestoque.produto.dto.LoginRequest;
import com.apiestoque.produto.dto.LoginResponse;
import com.apiestoque.produto.dto.RegisterRequest;
import com.apiestoque.produto.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = service.login(
                request.getUsername(),
                request.getPassword()
        );

        return new LoginResponse(token);
    }

    // =========================
    // CADASTRO
    // =========================
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request) {

        service.register(
                request.getUsername(),
                request.getPassword()
        );
    }
}