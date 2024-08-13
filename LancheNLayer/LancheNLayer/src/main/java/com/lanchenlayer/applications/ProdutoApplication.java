package com.lanchenlayer.applications;

import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProdutoApplication {
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    public ProdutoApplication(ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    public void adicionar(Produto produto) {
        this.produtoRepository.adicionar(produto);
        this.produtoService.salvarImagem(produto);
    }

    public void adicionarSoImagem(Produto produto) {
        this.produtoService.salvarImagem(produto);
    }

    public void remover(int id) {
        this.produtoRepository.remover(id);
    }

    public Produto buscarPorId(int id) {
        return this.produtoRepository.buscarPorId(id);
    }

    public ArrayList<Produto> buscarTodos() {
        return this.produtoRepository.buscarTodos();
    }

    public boolean atualizar(int id, String novaDescricao, float novoValor, String novoCaminhoImagem) {
        return produtoService.atualizar(id, novaDescricao, novoValor, novoCaminhoImagem);
    }

    public boolean vender(int id, int quantidade) {
        Produto produto = produtoRepository.buscarPorId(id);
        if (produto != null) {
            System.out.println("Vendendo " + quantidade + " unidades de " + produto.getDescricao());
            return true;
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
            return false;
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Remover produto");
            System.out.println("3. Atualizar produto");
            System.out.println("4. Buscar produto por ID");
            System.out.println("5. Listar todos os produtos");
            System.out.println("6. Vender produto");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("ID do produto: ");
                    int idAdicionar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Descrição do produto: ");
                    String descricaoAdicionar = scanner.nextLine();
                    System.out.print("Valor do produto: ");
                    float valorAdicionar = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.print("Caminho da imagem do produto: ");
                    String imagemAdicionar = scanner.nextLine();
                    adicionar(new Produto(idAdicionar, descricaoAdicionar, valorAdicionar, imagemAdicionar));
                    break;

                case 2:
                    System.out.print("ID do produto a remover: ");
                    int idRemover = scanner.nextInt();
                    remover(idRemover);
                    break;

                case 3:
                    System.out.print("ID do produto a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();
                    System.out.print("Novo valor: ");
                    float novoValor = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.print("Novo caminho da imagem: ");
                    String novoCaminhoImagem = scanner.nextLine();
                    atualizar(idAtualizar, novaDescricao, novoValor, novoCaminhoImagem);
                    break;

                case 4:
                    System.out.print("ID do produto a buscar: ");
                    int idBuscar = scanner.nextInt();
                    Produto produto = buscarPorId(idBuscar);
                    if (produto != null) {
                        System.out.println("Descrição: " + produto.getDescricao());
                        System.out.println("Valor: " + produto.getValor());
                        System.out.println("Imagem: " + produto.getImagem());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 5:
                    ArrayList<Produto> produtos = buscarTodos();
                    produtos.forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Descrição: " + p.getDescricao() + ", Valor: " + p.getValor() + ", Imagem: " + p.getImagem());
                    });
                    break;

                case 6:
                    System.out.print("ID do produto a vender: ");
                    int idVender = scanner.nextInt();
                    System.out.print("Quantidade a vender: ");
                    int quantidade = scanner.nextInt();
                    vender(idVender, quantidade);
                    break;

                case 7:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}
