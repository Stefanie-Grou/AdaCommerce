package org.example.Produto;

import org.example.Cliente.Cliente;

import java.util.Date;

public class Produto {
    private final int identificador;
    private String nome;
    private String categoria;
    private double valorProduto;
    private double valorVenda;
    private double valorDesconto;


    public Produto(int identificador,
                   String nome,
                   String categoria,
                   double valorProduto,
                   double valorVenda,
                   double valorDesconto) {
        this.identificador = identificador;
        this.nome = nome;
        this.categoria = categoria;
        this.valorProduto = valorProduto;
        this.valorVenda = valorVenda;
        this.valorDesconto = valorDesconto;
    }

    public void atualizarProduto(String nome,
                                 String categoria,
                                 double valorProduto,
                                 double valorVenda,
                                 double valorDesconto) {
        this.nome = nome;
        this.categoria = categoria;
        this.valorProduto = valorProduto;
        this.valorVenda = valorVenda;
        this.valorDesconto = valorDesconto;
    }

    public void listarProduto() {
        System.out.println("ID: " + identificador);
        System.out.println("Nome: " + nome);
        System.out.println("Categoria: " + categoria);
        System.out.println("Valor Produto: " + valorProduto);
        System.out.println("Valor Venda: " + valorVenda);
        System.out.println("Valor Desconto: " + valorDesconto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "identificador=" + identificador +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", valorProduto=" + valorProduto +
                ", valorVenda=" + valorVenda +
                ", valorDesconto=" + valorDesconto +
                '}';
    }
}

