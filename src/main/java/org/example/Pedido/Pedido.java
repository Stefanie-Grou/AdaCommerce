package org.example.Pedido;

import org.example.Produto.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private List<ItemPedido> itens;
    private String status;
    private Date dataCriacao;

    public Pedido(int id) {
        this.id = id;
        this.itens = new ArrayList<>();
        this.status = "Em aberto";
        this.dataCriacao = new Date();
    }

    public void adicionarItem(Produto produto, int quantidade, double valorVenda) {
        ItemPedido item = new ItemPedido(produto, quantidade, valorVenda);
        itens.add(item);
    }

//    public void removerItem(Produto produto) {
//        itens.removeIf(item -> item.getProduto().getNome().equals(produto.getNome()));
//    }
//
//    public void alterarQuantidadeItem(Produto produto, int novaQuantidade) {
//        for (ItemPedido item : itens) {
//            if (item.getProduto().getNome().equals(produto.getNome())) {
//                item.setQuantidade(novaQuantidade);
//                break;
//            }
//        }
//    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void finalizarPedido() throws PedidoException {
        if (itens.isEmpty()) {
            throw new PedidoException("Não é possível finalizar um pedido vazio.");
        }
        status = "Finalizado";
    }

    public void realizarPagamento() throws PagamentoException {
        if (!status.equals("Finalizado")) {
            throw new PagamentoException("O pagamento só pode ser realizado após a finalização do pedido.");
        }
        // Implementar lógica de pagamento aqui
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public int getId() {
        return id;
    }

    public Date getDataCriacao() {
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
}
