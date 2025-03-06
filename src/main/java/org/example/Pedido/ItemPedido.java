package org.example.Pedido;

import org.example.Produto.Produto;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double valorVenda;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
    }
}