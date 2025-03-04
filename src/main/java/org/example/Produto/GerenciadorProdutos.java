package org.example.Produto;

import org.example.BancoDeDados.BancoDeDadosProdutos;

public class GerenciadorProdutos {
    public static void criarProduto(
            int identificador,
            String nome,
            String categoria,
            double valorProduto,
            double valorVenda,
            double valorDesconto) {
        Produto novoProduto = new Produto(
                identificador,
                nome,
                categoria,
                valorProduto,
                valorVenda,
                valorDesconto);

        BancoDeDadosProdutos.adicionaProdutos(novoProduto);
        System.out.println("Produto adicionado com sucesso!");
    }
}
