package com.apiestoque.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apiestoque.produto.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

