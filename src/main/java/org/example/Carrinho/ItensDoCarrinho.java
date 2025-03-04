package org.example.Carrinho;

import java.util.ArrayList;
import java.util.List;

public class ItensDoCarrinho {
    private String identificador;
    private int quantidade;

    public void ItemCarrinho(String identificador, int quantidade) {
        this.identificador = identificador;
        this.quantidade = quantidade;
    }

    List<String> carrinho = new ArrayList<>();

    public void adicionarItens(String identificador, int quantidade) {
        carrinho.add(identificador);
        System.out.println(identificador + " adicionado com sucesso!");
    }

    public void exibirCarrinho() {
        System.out.println("Itens no carrinho: " + carrinho);
    }
}
