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

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public double getSubtotal() {
        return quantidade * valorVenda;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "produto=" + produto.getNome() +
                ", quantidade=" + quantidade +
                ", valorVenda=" + valorVenda +
                ", subtotal=" + getSubtotal() +
                '}';
    }
}