package org.example.Pedido;

import org.example.Produto.Produto;
import java.util.Scanner;

public class GerenciarPedido {
    private Pedido pedido;
    private Scanner scanner;

    public GerenciarPedido(int idPedido) {
        this.pedido = new Pedido(idPedido);
        this.scanner = new Scanner(System.in);
    }
}