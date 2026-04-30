package com.apiestoque.produto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario") // ✅ IMPORTANTE
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}