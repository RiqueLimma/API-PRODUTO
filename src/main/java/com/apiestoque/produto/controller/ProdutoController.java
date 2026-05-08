package com.apiestoque.produto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.apiestoque.produto.entity.Produto;
import com.apiestoque.produto.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(
            @PathVariable Long id,
            @RequestBody Produto produto) {

        return service.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}