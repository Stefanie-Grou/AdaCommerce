package org.example.Pedido;

import org.example.Carrinho.ItemCarrinho;
import org.example.Cliente.Cliente;
import org.example.Produto.Produto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.BancoDeDados.BancoDeDadosProdutos.listaProdutos;

public class Pedido {
    private int id;
    private static List<ItemPedido> itens;
    private static String status;
    private LocalDate dataCriacao;
    private Cliente cliente;
    private double total;

    public Pedido() {
        this.id = id;
        this.itens = itens == null ? new ArrayList<>() : itens;
        this.status = String.valueOf(StatusDePedido.ABERTO);
        this.dataCriacao = LocalDate.now();
        this.cliente = cliente;
        this.total = calcularTotal();
    }
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    public void adicionarItemAoCarrinho(Scanner sc) {
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
        for (Produto p : listaProdutos) {
            if (p.getIdentificador() == codigo) {
                return p;
            }
        }
        return null;
    }

    public void removerItem(Scanner sc) {
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

//    public void alterarQuantidadeItem(Produto produto, int novaQuantidade) {
//        for (ItemPedido item : itens) {
//            if (item.getProduto().getId() == produto.getId()) {
//                item.setQuantidade(novaQuantidade);
//                break;
//            }
//        }
//        total = calcularTotal();
//    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCarrinho item : itensCarrinho) {
            double valorVenda = item.getProduto().getValorVenda();
            int quantidade = item.getQuantidade();
            total += valorVenda * quantidade;
        }
        System.out.println("Total do carrinho: R$" + total);
        return total;
    }


    public static void fecharCarrinho() {
        if (itens.isEmpty()) {
            System.out.println("Não é possível finalizar um pedido vazio.");
            return;
        } else {
            status = String.valueOf(StatusDePedido.AGUARDANDO_PAGAMENTO);
        }
    }

    public static void realizarPagamento() {
        if (!status.equals(String.valueOf(StatusDePedido.AGUARDANDO_PAGAMENTO))) {
            System.out.println("O pagamento só pode ser realizado após a finalização do pedido.");
        } else {
            status = String.valueOf(StatusDePedido.APROVADO);}
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public static class PedidoException extends Exception {
        public PedidoException(String message) {
            super(message);
        }
    }

    public static class PagamentoException extends Exception {
        public PagamentoException(String message) {
            super(message);
        }
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