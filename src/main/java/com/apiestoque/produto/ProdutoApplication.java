package com.apiestoque.produto;

import com.apiestoque.produto.controller.ProdutoController;
import com.apiestoque.produto.repository.ProdutoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProdutoApplication {

	private final ProdutoRepository produtoRepository;
    private final ProdutoController produtoController;

    ProdutoApplication(ProdutoController produtoController, ProdutoRepository produtoRepository) {
        this.produtoController = produtoController;
        this.produtoRepository = produtoRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(ProdutoApplication.class, args);
	}

}
