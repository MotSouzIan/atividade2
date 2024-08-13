package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;

public class ProdutoRepository {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public Produto buscarPorId(int id) {
        Produto produtoInDb = produtos.stream().filter(p -> p.getId() == id).findFirst().get();

        return produtoInDb;
    }

    public ArrayList<Produto> buscarTodos() {
        return produtos;
    }

    public void atualizar(int id, String novaDescricao, float novoValor, String novoImagem) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produto.setDescricao(novaDescricao);
            produto.setValor(novoValor);
            produto.setImagem(novoImagem);
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }
    }
}
