package org.example.Pedido;

import org.example.Carrinho.ItemCarrinho;
import org.example.Cliente.Cliente;
import org.example.Notificacoes.Notificador;
import org.example.Produto.Produto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.BancoDeDados.BancoDeDadosProdutos.listaProdutos;

public class Pedido {
    private int id;
    private List<ItemPedido> itens;
    private String status;
    private LocalDate dataCriacao;
    private Cliente cliente;
    private double total;

    private List<ItemCarrinho> itensCarrinho;
    private List<Notificador> notificadores;

    public Pedido(IniciadorDoCarrinho iniciadorDoCarrinho, Cliente cliente, int id,List<Notificador> notificadores) {
        this.id = id;
        this.itens = new ArrayList<>();
        this.status = String.valueOf(StatusDePedido.ABERTO);
        this.dataCriacao = LocalDate.now();
        this.cliente = cliente;
        this.itensCarrinho = iniciadorDoCarrinho.getItensCarrinho();
        this.notificadores = notificadores;
        this.total = calcularTotal();
    }

    public void adicionarItemAoCarrinho(Scanner sc) {
        if (!aptoAlterarCarrinho()) {
            return;
        }
        boolean exit = false;
        do {
            System.out.println("Digite o código do produto ou 0 para sair: ");
            int inputCode = sc.nextInt();

            if (inputCode == 0) {
                exit = true;
            } else {
                Produto produto = buscarProdutoPorCodigo(inputCode);
                if (produto == null) {
                    System.out.println("Produto não encontrado. Tente novamente.");
                    continue;
                }

                System.out.println("Digite a quantidade do produto desejado: ");
                int quantidade = sc.nextInt();

                if (quantidade <= 0) {
                    System.out.println("Quantidade inválida. Tente novamente.");
                    continue;
                }
                itensCarrinho.add(new ItemCarrinho(produto, quantidade));
            }
        } while (!exit);
        total = calcularTotal();
    }

    private Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : listaProdutos) {
            if (produto.getIdentificador() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public void removerItem(Scanner sc) {
        if (!aptoAlterarCarrinho()) {
            return;
        }
        System.out.println("Digite o identificador do produto a ser removido: ");
        int inputCode = sc.nextInt();
        if (inputCode == 0) {
            System.out.println("Código inválido.");
            return;
        }
        boolean itemRemovido = itensCarrinho.removeIf(item -> item.getProduto().getIdentificador() == inputCode);

        if (itemRemovido) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado no carrinho.");
        }
        total = calcularTotal();
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCarrinho item : itensCarrinho) {
            double valorVenda = item.getProduto().getValorVenda();
            int quantidade = item.getQuantidade();
            total += valorVenda * quantidade;
        }
        System.out.printf("Total do carrinho: R$ %.2f\n", total);
        return total;
    }

    public void fecharCarrinho() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("Não é possível finalizar um pedido vazio.");
            return;
        } else {
            status = String.valueOf(StatusDePedido.AGUARDANDO_PAGAMENTO);
            System.out.println("Novo status para o pedido: " + status);
            notificarCliente("Seu pedido foi fechado e está aguardando pagamento.");
        }
    }

    private void notificarCliente(String mensagem) {
        notificadores.forEach(notificador -> notificador.notificar(mensagem));
    }

    public void realizarPagamento() {
        if (!status.equals(String.valueOf(StatusDePedido.AGUARDANDO_PAGAMENTO))) {
            System.out.println("O pagamento só pode ser realizado após a finalização do pedido.");
        } else {
            status = String.valueOf(StatusDePedido.PAGAMENTO_APROVADO);
            System.out.println("Pagamento aprovado com sucesso.");
//            notificarCliente("Seu pagamento foi aprovado com sucesso.");
        }
    }

    public void alterarQuantidade(Scanner sc) {
        if (!aptoAlterarCarrinho()) {
            return;
        }
        boolean exit = false;
        do {
            System.out.println("Digite o código do produto ou 0 para sair: ");
            int inputCode = sc.nextInt();

            if (inputCode == 0) {
                exit = true;
            } else {
                Produto produto = buscarProdutoPorCodigo(inputCode);
                if (produto == null) {
                    System.out.println("Produto não encontrado. Tente novamente.");
                    continue;
                }

                System.out.println("Digite a quantidade do produto desejado: ");
                int quantidade = sc.nextInt();

                if (quantidade <= 0) {
                    System.out.println("Quantidade inválida. Tente novamente.");
                    continue;
                }

                boolean itemAtualizado = false;
                for (ItemCarrinho item : itensCarrinho) {
                    if (item.getProduto().getIdentificador() == produto.getIdentificador()) {
                        item.setQuantidade(quantidade);
                        itemAtualizado = true;
                        break;
                    }
                }

                if (!itemAtualizado) {
                    itensCarrinho.add(new ItemCarrinho(produto, quantidade));
                }
            }
        } while (!exit);
    }

    public boolean aptoAlterarCarrinho() {
        String statusDoPedido = getStatus();
        if (!statusDoPedido.equals(String.valueOf(StatusDePedido.ABERTO))) {
            System.out.println("Apenas pedidos em aberto podem ser alterados.");
            return false;
        }
        return true;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", itens=" + itens +
                ", status='" + status + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", cliente=" + cliente +
                ", total=" + total +
                ", itensCarrinho=" + itensCarrinho +
                '}';
    }
}
