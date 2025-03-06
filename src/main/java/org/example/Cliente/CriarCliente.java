package org.example.Cliente;

import java.util.Scanner;

import static org.example.BancoDeDados.BancoDeDadosClientes.listaClientes;

public class CriarCliente {
    public static Cliente cadastrarCliente() {
        final int CPF_SIZE = 11;
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com os seguintes dados do cliente:");
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        boolean valid = false;
        String numeroCPF;
        do {
            System.out.print("Número do CPF: ");
            numeroCPF = sc.nextLine();
            int intLength = numeroCPF.length();
            if (intLength == CPF_SIZE) {
                valid = true;
            } else {
                System.out.println("Erro: O CPF deve ter " + CPF_SIZE + " dígitos.");
            }
        } while (!valid);

        System.out.print("E-mail: ");
        String email = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("CEP: ");
        String cep = sc.nextLine();

        System.out.print("Número da residência: ");
        String numeroDaResidencia = sc.nextLine();

        System.out.print("Complemento: ");
        String complemento = sc.nextLine();

        Cliente novoCliente = new Cliente(nome, numeroCPF, email, telefone, cep, numeroDaResidencia, complemento);

        listaClientes.add(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
        return novoCliente;
    }
}
