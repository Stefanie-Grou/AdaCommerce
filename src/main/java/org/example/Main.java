package org.example;

import org.example.BancoDeDados.BancoDeDadosProdutos;
import org.example.Carrinho.ItemCarrinho;
import org.example.GerenciadorGeral.GerenciadorDoMenu;
import org.example.Pedido.IniciadorDoCarrinho;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        IniciadorDoCarrinho itensDoCarrinho = new IniciadorDoCarrinho();
        List<ItemCarrinho> itensCarrinho = itensDoCarrinho.getItensCarrinho();

        BancoDeDadosProdutos.criarProdutosExemplo();
        GerenciadorDoMenu.exibirMenu();
    }
}