package org.example.BancoDeDados;

import org.example.Produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosProdutos {
    public static List<Produto> listaProdutos = new ArrayList<>();

    public static void adicionaProdutos(Produto produto) {
        listaProdutos.add(produto);
    }

    public static void imprimirProdutos() {
        for (Produto cliente : listaProdutos) {
            System.out.println(cliente);
        }
    }

    public static void imprimeUltimoProduto() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Sem produtos cadastrados!");
        }
        System.out.println(listaProdutos.get(listaProdutos.size() - 1));
    }
}
