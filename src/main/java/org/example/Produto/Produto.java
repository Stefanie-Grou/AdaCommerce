package org.example.Produto;

public class Produto {
    private final String identificador;
    private String nome;
    private String categoria;
    private double valorProduto;
    private double valorVenda;
    private double valorDesconto;

    public Produto(String identificador,
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
}

