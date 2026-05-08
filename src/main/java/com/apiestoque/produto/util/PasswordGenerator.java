package com.apiestoque.produto.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senha = "123";
        String hash = encoder.encode(senha);
        System.out.println("Senha: " + senha);
        System.out.println("Hash gerado:");
        System.out.println(hash);
    }
}
