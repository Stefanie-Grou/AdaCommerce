package org.example;

import org.example.BancoDeDados.BancoDeDadosProdutos;
import org.example.GerenciadorGeral.GerenciadorDoMenu;
import org.example.Pedido.Pedido;

import static org.example.BancoDeDados.BancoDeDadosClientes.listaClientes;

public class Main {
    public static void main(String[] args) {
        BancoDeDadosProdutos.criarProdutosExemplo();
        GerenciadorDoMenu.exibirMenu();
    }
}