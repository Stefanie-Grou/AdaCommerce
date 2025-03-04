package org.example.Cliente;

import java.util.Scanner;
import static org.example.BancoDeDados.BancoDeDadosClientes.listaClientes;

public class AtualizarCliente {
    public static void atualizarCliente() {
        System.out.println("Digite o CPF do cliente que deseja atualizar: ");
        Scanner sc = new Scanner(System.in);
        String cpf = sc.nextLine();

        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        Cliente clienteEncontrado = null;
        for (Cliente cliente : listaClientes) {
            if (cliente.getNumeroCPF().equals(cpf)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: " + clienteEncontrado.getNome());
            System.out.println("Atualize os dados do cliente (deixe em branco para manter o mesmo valor):");

            System.out.print("Novo Nome: ");
            String novoNome = sc.nextLine();
            if (!novoNome.isBlank()) {
                clienteEncontrado.setNome(novoNome);
            }

            System.out.print("Novo E-mail: ");
            String novoEmail = sc.nextLine();
            if (!novoEmail.isBlank()) {
                clienteEncontrado.setEmail(novoEmail);
            }

            System.out.print("Novo Telefone: ");
            String novoTelefone = sc.nextLine();
            if (!novoTelefone.isBlank()) {
                clienteEncontrado.setTelefone(novoTelefone);
            }

            System.out.print("Novo CEP: ");
            String novoCep = sc.nextLine();
            if (!novoCep.isBlank()) {
                clienteEncontrado.setCep(novoCep);
            }

            System.out.print("Novo Número da Residência: ");
            String novoNumeroResidencia = sc.nextLine();
            if (!novoNumeroResidencia.isBlank()) {
                clienteEncontrado.setNumeroDaResidencia(novoNumeroResidencia);
            }

            System.out.print("Novo Complemento: ");
            String novoComplemento = sc.nextLine();
            if (!novoComplemento.isBlank()) {
                clienteEncontrado.setComplementoDoEndereco(novoComplemento);
            }

            System.out.println("Dados atualizados com sucesso!");
        } else {
            System.out.println("Erro: Cliente com o CPF fornecido não encontrado.");
        }
    }
}
