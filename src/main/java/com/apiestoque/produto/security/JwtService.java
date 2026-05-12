package com.apiestoque.produto.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "chave-super-secreta-com-mais-de-32-caracteres";

public String gerarToken(String username, String role) {
    return Jwts.builder()
            .setSubject(username) // usuário
            .claim("role", role)  // perfil/role
            .setIssuedAt(new Date()) // data de emissão
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // expira em 1h
            .signWith(
                Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),
                io.jsonwebtoken.SignatureAlgorithm.HS256 // algoritmo padrão
            )
            .compact();
}


    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}
