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
        for (Produto produto : listaProdutos) {
            System.out.println(produto);
        }
    }

    public static void imprimeUltimoProduto() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Sem produtos cadastrados!");
        } else {
            System.out.println(listaProdutos.get(listaProdutos.size() - 1));
        }
    }

    public static void criarProdutosExemplo() {
        Produto camisetaReta = new Produto(1, "Camiseta Reta", "Vestuário", 30.48, 50.49, 28.45);
        Produto smartPhone = new Produto(2, "Moto G34", "Eletrônicos", 950.78, 1034.89, 200.58);
        Produto ventilador = new Produto(3, "Ventilador Ventisol", "Eletrodoméstico", 150.74, 261.41, 80.21);
        Produto foneDeOuvido = new Produto(4, "Fone Philips", "Eletrônicos", 97.69, 139.63, 60);
        Produto relogio = new Produto(5, "Relógio Casio", "Acessórios", 100.00, 150.00, 80.00);
        Produto laptopDell = new Produto(6, "Laptop Dell Inspiron", "Eletrônicos", 2500.00, 2800.00, 500.00);
        Produto geladeira = new Produto(7, "Geladeira Brastemp", "Eletrodoméstico", 1500.00, 1999.99, 500.00);
        Produto tenisNike = new Produto(8, "Tênis Nike Air", "Vestuário", 150.00, 250.00, 100.00);
        Produto tvSamsung = new Produto(9, "Televisão Samsung 50\"", "Eletrônicos", 2000.00, 3000.00, 500.00);
        Produto microondas = new Produto(10, "Micro-ondas LG", "Eletrodoméstico", 400.00, 600.00, 150.00);

        BancoDeDadosProdutos.adicionaProdutos(camisetaReta);
        BancoDeDadosProdutos.adicionaProdutos(smartPhone);
        BancoDeDadosProdutos.adicionaProdutos(ventilador);
        BancoDeDadosProdutos.adicionaProdutos(foneDeOuvido);
        BancoDeDadosProdutos.adicionaProdutos(relogio);
        BancoDeDadosProdutos.adicionaProdutos(laptopDell);
        BancoDeDadosProdutos.adicionaProdutos(geladeira);
        BancoDeDadosProdutos.adicionaProdutos(tenisNike);
        BancoDeDadosProdutos.adicionaProdutos(tvSamsung);
        BancoDeDadosProdutos.adicionaProdutos(microondas);
    }
}