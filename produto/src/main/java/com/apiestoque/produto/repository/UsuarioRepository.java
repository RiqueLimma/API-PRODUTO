package com.apiestoque.produto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apiestoque.produto.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}