package org.example.Cliente;

import static org.example.BancoDeDados.BancoDeDadosClientes.listaClientes;

public class listarClientes {
    public static void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente.toString());
            }
        }
    }
}
