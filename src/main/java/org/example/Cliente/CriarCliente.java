package org.example.Cliente;

import java.util.Scanner;

public class CriarCliente {
    public static void cadastrarCliente() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Entre com os seguintes dados do cliente:");
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Número do CPF ");
            String idNumber = sc.nextLine();

            System.out.print("E-mail: ");
            String email = sc.nextLine();

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            System.out.print("CEP: ");
            String cep = sc.nextLine();

            System.out.print("Número da residência: ");
            String numeroDaResidencia = sc.nextLine();

            System.out.print("Complemento:");
            String complemento = sc.nextLine();

            Cliente novoCliente = new Cliente(nome, idNumber, email, telefone, cep, numeroDaResidencia, complemento);

            System.out.println("Cliente cadastrado com sucesso!");
            break;
        }
    }
}
