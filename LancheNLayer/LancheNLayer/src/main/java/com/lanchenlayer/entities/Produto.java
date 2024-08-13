package com.lanchenlayer.entities;

public class Produto {
    private int id;
    private String descricao;
    private float valor;
    private String imagem;

    public Produto(int id, String descricao, float valor, String imagem) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
