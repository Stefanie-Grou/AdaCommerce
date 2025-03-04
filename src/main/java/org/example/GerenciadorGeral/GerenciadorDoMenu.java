package org.example.GerenciadorGeral;

import org.example.BancoDeDados.BancoDeDadosClientes;
import org.example.BancoDeDados.BancoDeDadosProdutos;
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
            System.out.println("1. Listar Produtos");
            System.out.println("2. Adicionar Item ao Carrinho");
            System.out.println("3. Remover Item");
            System.out.println("4. Alterar Quantidade");
            System.out.println("5. Calcular Total");
            System.out.println("6. Finalizar Pedido");
            System.out.println("7. Realizar Pagamento");
            System.out.println("8. Exibir Pedido");
            System.out.println("9. Cadastrar o Cliente");
            System.out.println("10. Listar clientes");
            System.out.println("11. Atualizar cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            Pedido pedido = new Pedido();
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
                    System.out.print("Identificador do produto: ");
//                    identificador = scanner.nextLine();
//                    System.out.print("Nova quantidade: ");
//                    quantidade = scanner.nextInt();
//                    scanner.nextLine();
////                    pedido.alterarQuantidadeItem(new Produto(identificador, "", "", 0, 0, 0), quantidade);
//                    System.out.println("Quantidade alterada.");
                    break;

                case 5:
                    pedido.calcularTotal();
                    break;

                case 6:
                    Pedido.fecharCarrinho();
                    break;

                case 7:
                    Pedido.realizarPagamento();
                    break;

                case 8:
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

                case 9:
                    CriarCliente.cadastrarCliente();
                    break;

                case 10:
                    listarClientes.listarClientes();
                    break;

                case 11:
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
