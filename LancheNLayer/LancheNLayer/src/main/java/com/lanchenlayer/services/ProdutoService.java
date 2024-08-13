package com.lanchenlayer.services;

import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ProdutoService {
    private String caminhoDestino = "C:\\Users\\aluno\\LancheNLayer\\src\\main\\resources\\images\\";
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public static String getFileExtension(String filePath) {
        String fileName = new File(filePath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "No extension" : fileName.substring(dotIndex + 1);
    }

    public boolean salvarImagem(Produto produto) {
        Path path = Paths.get(produto.getImagem());

        Path pastaDestino = Paths.get(String.format("%s%d.%s", caminhoDestino, produto.getId(), getFileExtension(produto.getImagem())));

        if (Files.exists(path)) {
            try {
                Files.copy(path, pastaDestino, StandardCopyOption.REPLACE_EXISTING);
                produto.setImagem(pastaDestino.getFileName().toString());
                return true;
            } catch (Exception ex)
            {
                return false;
            }
        }

        return false;
    }

    public boolean removerImagem(int produtoId) {
        Produto produto = produtoRepository.buscarPorId(produtoId);
        if (produto != null) {
            Path path = Paths.get(caminhoDestino + produto.getImagem());
            try {
                Files.deleteIfExists(path);
                produto.setImagem(null); // Opcional: limpar o caminho da imagem do produto
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Produto com ID " + produtoId + " não encontrado.");
            return false;
        }
    }

    public boolean atualizar(int produtoId, String novaDescricao, float novoValor, String novoCaminhoImagem) {
        Produto produto = produtoRepository.buscarPorId(produtoId);
        if (produto != null) {
            produto.setDescricao(novaDescricao);
            produto.setValor(novoValor);

            if (novoCaminhoImagem != null && !novoCaminhoImagem.isEmpty()) {
                removerImagem(produtoId);
                produto.setImagem(novoCaminhoImagem);
                salvarImagem(produto);
            }

            produtoRepository.atualizar(produtoId, novaDescricao, novoValor, produto.getImagem());
            return true;
        } else {
            System.out.println("Produto com ID " + produtoId + " não encontrado.");
            return false;
        }
    }
}
