package org.example.GerenciadorGeral;

import org.example.BancoDeDados.BancoDeDadosClientes;
import org.example.Cliente.AtualizarCliente;
import org.example.Cliente.CriarCliente;
import org.example.Cliente.listarClientes;
import org.example.Pedido.ItemPedido;
import org.example.Pedido.Pedido;
import org.example.Produto.Produto;
import org.example.Cliente.Cliente;
import java.util.Scanner;

public class GerenciadorDoMenu {
    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Menu de Pedido =====");
            System.out.println("1. Adicionar Item ao Carrinho");
            System.out.println("2. Remover Item");
            System.out.println("3. Alterar Quantidade");
            System.out.println("4. Calcular Total");
            System.out.println("5. Finalizar Pedido");
            System.out.println("6. Realizar Pagamento");
            System.out.println("7. Exibir Pedido");
            System.out.println("8. Cadastrar o Cliente");
            System.out.println("9. Listar clientes");
            System.out.println("10. Atualizar cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Identificador do produto: ");
                    String identificador = scanner.nextLine();
                    System.out.print("Nome do produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Valor do produto: ");
                    double valorProduto = 0; //será alterado
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    double valorVenda = scanner.nextDouble();
                    double valorDesconto = 0; // será alterado
                    String categoria = ""; // sera alterado


                    Produto produto = new Produto(identificador, nomeProduto, categoria, valorProduto, valorVenda, valorDesconto);
                    scanner.nextLine();
                    System.out.println("Item adicionado ao pedido.");

                case 2:
                    System.out.print("Identificador do produto a remover: ");
                    identificador = scanner.nextLine();
//                    pedido.removerItem(new Produto(identificador, "", "", 0, 0, 0));
                    System.out.println("Item removido.");
                    break;

                case 3:
                    System.out.print("Identificador do produto: ");
                    identificador = scanner.nextLine();
                    System.out.print("Nova quantidade: ");
                    quantidade = scanner.nextInt();
                    scanner.nextLine();
//                    pedido.alterarQuantidadeItem(new Produto(identificador, "", "", 0, 0, 0), quantidade);
                    System.out.println("Quantidade alterada.");
                    break;

                case 4:
//                    System.out.println("Total do pedido: R$ " + pedido.calcularTotal());
                    break;

                case 5:
//                    try {
////                        pedido.finalizarPedido();
//                        System.out.println("Pedido finalizado.");
//                    } catch (Pedido.PedidoException e) {
//                        System.out.println("Erro: " + e.getMessage());
//                    }
                    break;

                case 6:
//                    try {
//                        pedido.realizarPagamento();
//                        System.out.println("Pagamento realizado com sucesso.");
//                    } catch (Pedido.PagamentoException e) {
//                        System.out.println("Erro: " + e.getMessage());
//                    }
                    break;

                case 7:
//                    System.out.println("ID do Pedido: " + pedido.getId());
//                    System.out.println("Pedido criado em: " + pedido.getDataCriacao());
//                    System.out.println("Status: " + pedido.getStatus());
//                    System.out.println("Itens do pedido:");
//                    for (ItemPedido item : pedido.getItens()) {
//                        Produto itemProduto = item.getProduto();
//                        //System.out.println("ID: " + itemProduto.getIdentificador() +
//                        System.out.println("ID: " +
//                                " - Nome: " + itemProduto.getNome() +
//                                " - Valor Venda: R$ " + itemProduto.getValorVenda() +
//                                " - Quantidade: " + item.getQuantidade() +
//                                " - Subtotal: R$ " + item.getSubtotal());
//                    }
                    break;

                case 8:
//                    BancoDeDadosClientes.cadastrarCliente();
                    CriarCliente.cadastrarCliente();
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
