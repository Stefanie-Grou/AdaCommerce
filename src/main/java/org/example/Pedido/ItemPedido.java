package org.example.Pedido;

import org.example.Produto.Produto;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double valorVenda;

    public ItemPedido(Produto produto, int quantidade, double valorVenda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
    }

    public double getSubtotal() {
        return quantidade * valorVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorVenda() {
        return valorVenda;
    }
}