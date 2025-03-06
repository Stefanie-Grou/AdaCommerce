package org.example.GerenciadorGeral;

import org.example.BancoDeDados.BancoDeDadosProdutos;
import org.example.Cliente.AtualizarCliente;
import org.example.Cliente.CriarCliente;
import org.example.Cliente.listarClientes;
import org.example.Notificacoes.NotificadorEmail;
import org.example.Notificacoes.NotificadorSMS;
import org.example.Pedido.IniciadorDoCarrinho;
import org.example.Pedido.Pedido;
import org.example.Cliente.Cliente;
import org.example.Notificacoes.Notificador;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDoMenu {
    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        IniciadorDoCarrinho itensDoCarrinho = new IniciadorDoCarrinho();
        Cliente cliente = CriarCliente.cadastrarCliente();
        List<Notificador> notificadores = Arrays.asList(
                new NotificadorEmail(cliente.getEmail()),
                new NotificadorSMS(cliente.getTelefone())
        );
        Pedido pedido = new Pedido(itensDoCarrinho, cliente, 1,notificadores);

        while (true) {
            System.out.println("\n===== Menu de Pedido =====");
            System.out.println("1. Listar Produtos em Estoque");
            System.out.println("2. Adicionar Item ao Carrinho");
            System.out.println("3. Remover Item");
            System.out.println("4. Alterar Quantidade");
            System.out.println("5. Calcular Total");
            System.out.println("6. Finalizar Pedido");
            System.out.println("7. Realizar Pagamento");
            System.out.println("8. Exibir Pedido");
            System.out.println("9. Listar clientes");
            System.out.println("10. Atualizar cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    BancoDeDadosProdutos.imprimirProdutos();
                    break;
                case 2:
                    pedido.adicionarItemAoCarrinho(scanner);
                    break;
                case 3:
                    pedido.removerItem(scanner);
                    break;

                case 4:
                    pedido.alterarQuantidade(scanner);
                    break;

                case 5:
                    pedido.calcularTotal();
                    break;

                case 6:
                    pedido.fecharCarrinho();
                    break;

                case 7:
                    pedido.realizarPagamento();
                    break;

                case 8:
                    System.out.println(pedido);
                    break;

                case 9:
                    listarClientes.listarClientes();
                    break;

                case 10:
                    AtualizarCliente.atualizarCliente();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
