package org.example;

import org.example.Cliente.Cliente;
import org.example.Pedido.GerenciarPedido;
import org.example.Produto.TesteProduto;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente();
        System.out.println(cliente1.toString());

        TesteProduto.executarTeste(); // apagar depois - apenas teste

        GerenciarPedido gerenciarPedido = new GerenciarPedido(1);
        gerenciarPedido.exibirMenu();
    }
}