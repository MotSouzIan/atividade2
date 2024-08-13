package com.lanchenlayer;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class Console
{
    public static void main( String[] args )
    {
        ProdutoRepository produtoRepository = new ProdutoRepository();
        ProdutoService produtoService = new ProdutoService(produtoRepository);
        ProdutoApplication produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
        ProdutoFacade produtoFacade = new ProdutoFacade(produtoApplication);

        produtoFacade.adicionar(new Produto(1, "Cachorro quente", 4.00f, "C:\\Users\\aluno\\imagens\\cachorroquente.jpg"));
        produtoFacade.adicionar(new Produto(2, "X-Salada", 5.00f, "C:\\Users\\aluno\\imagens\\xsalada.jpg"));

        ArrayList<Produto> produtos = produtoFacade.buscarTodos();

        produtoApplication.menu();

        System.out.println("Descrição");
        produtos.forEach(c -> {
            System.out.println(c.getDescricao());
        });
    }
}
