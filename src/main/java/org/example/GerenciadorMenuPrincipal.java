package org.example.GerenciadorGeral;

import org.example.BancoDeDados.BancoDeDadosProdutos;
import org.example.Cliente.CriarCliente;
import org.example.Cliente.Cliente;
import org.example.Notificacoes.Notificador;
import org.example.Notificacoes.NotificadorEmail;
import org.example.Notificacoes.NotificadorSMS;
import org.example.Pedido.IniciadorDoCarrinho;
import org.example.Pedido.Pedido;
import org.example.Produto.Produto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GerenciadorMenuPrincipal {

    private static int proximoIdPedido = 1;

    public static void exibirMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inicializando o sistema...");
        BancoDeDadosProdutos.criarProdutosExemplo();

        boolean sair = false;
        while (!sair) {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1. Realizar Compra");
            System.out.println("2. Gerenciar Pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do sistema. Obrigado!");
                    sair = true;
                    break;
                case 1:
                    realizarCompra(scanner);
                    break;
                case 2:
                    gerenciarPedido(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    private static void realizarCompra(Scanner scanner) {
        IniciadorDoCarrinho carrinhoDeCompras = new IniciadorDoCarrinho();

        boolean continuarComprando = true;
        boolean primeiroProduto = true;

        while (continuarComprando) {
            if (primeiroProduto) {
                System.out.println("\n=== Produtos Disponíveis ===");
                listarProdutosParaCompra();
                primeiroProduto = false;
            }

            System.out.println("\nDigite o código do produto que deseja adicionar ao carrinho ou 0 para finalizar: ");
            int codigoProduto = scanner.nextInt();
            scanner.nextLine();

            if (codigoProduto == 0) {
                if (carrinhoDeCompras.getItensCarrinho().isEmpty()) {
                    System.out.println("Carrinho vazio. Voltando ao menu principal.");
                    return;
                }
                continuarComprando = false;
            } else {
                Produto produto = buscarProdutoPorCodigo(codigoProduto);
                if (produto != null) {
                    System.out.println("Digite a quantidade desejada: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    if (quantidade > 0) {
                        adicionarItemAoCarrinho(carrinhoDeCompras, produto, quantidade);

                        System.out.println("\nProduto adicionado ao carrinho!");
                        exibirCarrinho(carrinhoDeCompras);

                        System.out.println("\nDeseja adicionar mais produtos? (1-Sim/0-Não)");
                        int continuar = scanner.nextInt();
                        scanner.nextLine();

                        if (continuar == 0) {
                            continuarComprando = false;
                        } else {
                            System.out.println("\n=== Produtos Disponíveis ===");
                            listarProdutosParaCompra();
                        }
                    } else {
                        System.out.println("Quantidade inválida. Por favor, tente novamente.");
                    }
                } else {
                    System.out.println("Produto não encontrado. Por favor, tente novamente.");
                }
            }
        }

        System.out.println("\nProsseguindo para o cadastro do cliente...");
        aguardarTecla(scanner);

        Cliente cliente = CriarCliente.cadastrarCliente();

        System.out.println("\nCadastro concluído com sucesso!");
        aguardarTecla(scanner);

        List<Notificador> notificadores = Arrays.asList(
                new NotificadorEmail(cliente.getEmail()),
                new NotificadorSMS(cliente.getTelefone())
        );

        Pedido novoPedido = new Pedido(carrinhoDeCompras, cliente, proximoIdPedido++, notificadores);

        System.out.println("\nPedido criado com sucesso!");
        System.out.println(novoPedido);
        aguardarTecla(scanner);

        gerenciarPedidoEspecifico(scanner, novoPedido);
    }

    private static void listarProdutosParaCompra() {
        for (Produto produto : BancoDeDadosProdutos.listaProdutos) {
            System.out.printf("%d. %s - Categoria: %s - Preço: R$ %.2f%n",
                    produto.getIdentificador(),
                    produto.getNome(),
                    produto.getCategoria(),
                    produto.getValorVenda());
        }
    }

    private static Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : BancoDeDadosProdutos.listaProdutos) {
            if (produto.getIdentificador() == codigo) {
                return produto;
            }
        }
        return null;
    }

    private static void adicionarItemAoCarrinho(IniciadorDoCarrinho carrinho, Produto produto, int quantidade) {
        boolean produtoExistente = false;

        for (int i = 0; i < carrinho.getItensCarrinho().size(); i++) {
            if (carrinho.getItensCarrinho().get(i).getProduto().getIdentificador() == produto.getIdentificador()) {
                int novaQuantidade = carrinho.getItensCarrinho().get(i).getQuantidade() + quantidade;
                carrinho.getItensCarrinho().get(i).setQuantidade(novaQuantidade);
                produtoExistente = true;
                break;
            }
        }

        if (!produtoExistente) {
            org.example.Carrinho.ItemCarrinho novoItem = new org.example.Carrinho.ItemCarrinho(produto, quantidade);
            carrinho.getItensCarrinho().add(novoItem);
        }
    }

    private static void exibirCarrinho(IniciadorDoCarrinho carrinho) {
        double total = 0;

        System.out.println("\n=== Itens no Carrinho ===");

        if (carrinho.getItensCarrinho().isEmpty()) {
            System.out.println("Carrinho vazio");
            return;
        }

        for (org.example.Carrinho.ItemCarrinho item : carrinho.getItensCarrinho()) {
            double subtotal = item.getQuantidade() * item.getProduto().getValorVenda();
            total += subtotal;

            System.out.printf("- %s - Quantidade: %d - Preço unitário: R$ %.2f - Subtotal: R$ %.2f%n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getValorVenda(),
                    subtotal);
        }

        System.out.printf("Total do carrinho: R$ %.2f%n", total);
    }

    private static void gerenciarPedido(Scanner scanner) {
        System.out.println("\nDigite o ID do pedido que deseja gerenciar: ");
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nSimulando busca de pedido...");
        System.out.println("Por favor, forneça os dados do cliente para este pedido:");

        Cliente cliente = CriarCliente.cadastrarCliente();

        List<Notificador> notificadores = Arrays.asList(
                new NotificadorEmail(cliente.getEmail()),
                new NotificadorSMS(cliente.getTelefone())
        );

        IniciadorDoCarrinho carrinhoVazio = new IniciadorDoCarrinho();
        Pedido pedidoSimulado = new Pedido(carrinhoVazio, cliente, idPedido, notificadores);

        System.out.println("\nPedido encontrado!");
        aguardarTecla(scanner);

        gerenciarPedidoEspecifico(scanner, pedidoSimulado);
    }

    private static void gerenciarPedidoEspecifico(Scanner scanner, Pedido pedido) {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n===== Gerenciamento de Pedido =====");
            System.out.println("1. Adicionar item ao carrinho");
            System.out.println("2. Remover item do carrinho");
            System.out.println("3. Alterar quantidade de item");
            System.out.println("4. Visualizar carrinho");
            System.out.println("5. Calcular total do pedido");
            System.out.println("6. Finalizar pedido (fechar carrinho)");
            System.out.println("7. Realizar pagamento");
            System.out.println("8. Realizar entrega");
            System.out.println("9. Finalizar entrega");
            System.out.println("10. Exibir detalhes do pedido");
            System.out.println("0. Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    voltar = true;
                    break;
                case 1:
                    pedido.adicionarItemAoCarrinho(scanner);
                    aguardarTecla(scanner);
                    break;
                case 2:
                    pedido.removerItem(scanner);
                    aguardarTecla(scanner);
                    break;
                case 3:
                    pedido.alterarQuantidade(scanner);
                    aguardarTecla(scanner);
                    break;
                case 4:
                    pedido.exibirItensCarrinho();
                    aguardarTecla(scanner);
                    break;
                case 5:
                    pedido.calcularTotal();
                    aguardarTecla(scanner);
                    break;
                case 6:
                    pedido.fecharCarrinho();
                    aguardarTecla(scanner);
                    break;
                case 7:
                    pedido.realizarPagamento();
                    aguardarTecla(scanner);
                    break;
                case 8:
                    pedido.realizarEntrega();
                    aguardarTecla(scanner);
                    break;
                case 9:
                    pedido.finalizarEntrega();
                    aguardarTecla(scanner);
                    break;
                case 10:
                    System.out.println(pedido);
                    aguardarTecla(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    aguardarTecla(scanner);
            }
        }
    }

    private static void aguardarTecla(Scanner scanner) {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}