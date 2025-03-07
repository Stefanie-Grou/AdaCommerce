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
    private StatusDePedido status;
    private LocalDate dataCriacao;
    private Cliente cliente;
    private double total;

    private List<ItemCarrinho> itensCarrinho;
    private List<Notificador> notificadores;

    public Pedido(IniciadorDoCarrinho iniciadorDoCarrinho, Cliente cliente, int id, List<Notificador> notificadores) {
        this.id = id;
        this.itens = new ArrayList<>();
        this.status = StatusDePedido.ABERTO;
        this.dataCriacao = LocalDate.now();
        this.cliente = cliente;
        this.itensCarrinho = iniciadorDoCarrinho.getItensCarrinho();
        this.notificadores = notificadores;
        this.total = calcularTotal();
    }

    // Adicionar o método getId()
    public int getId() {
        return id;
    }

    public void adicionarItemAoCarrinho(Scanner sc) {
        if (!aptoAlterarCarrinho()) {
            return;
        }

        System.out.println("\n=== Produtos Disponíveis ===");
        for (Produto produto : listaProdutos) {
            System.out.printf("%d. %s - R$ %.2f%n",
                    produto.getIdentificador(),
                    produto.getNome(),
                    produto.getValorVenda());
        }

        boolean exit = false;
        do {
            System.out.println("\nDigite o código do produto ou 0 para sair: ");
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

                // Verifica se o produto já existe no carrinho
                boolean produtoExistente = false;
                for (ItemCarrinho item : itensCarrinho) {
                    if (item.getProduto().getIdentificador() == produto.getIdentificador()) {
                        item.setQuantidade(item.getQuantidade() + quantidade);
                        produtoExistente = true;
                        System.out.println("Quantidade atualizada no carrinho!");
                        break;
                    }
                }

                if (!produtoExistente) {
                    itensCarrinho.add(new ItemCarrinho(produto, quantidade));
                    System.out.println("Produto adicionado ao carrinho!");
                }

                System.out.println("Deseja adicionar mais produtos? (1-Sim/0-Não)");
                int continuar = sc.nextInt();
                if (continuar == 0) {
                    exit = true;
                }
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

        if (itensCarrinho.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }

        System.out.println("\n=== Itens no Carrinho ===");
        for (int i = 0; i < itensCarrinho.size(); i++) {
            ItemCarrinho item = itensCarrinho.get(i);
            System.out.printf("%d. %s - Quantidade: %d - Valor unitário: R$ %.2f%n",
                    item.getProduto().getIdentificador(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getValorVenda());
        }

        System.out.println("\nDigite o identificador do produto a ser removido: ");
        int inputCode = sc.nextInt();

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

        if (!itensCarrinho.isEmpty()) {
            System.out.printf("Total do carrinho: R$ %.2f%n", total);
        }

        return total;
    }

    public void fecharCarrinho() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("Não é possível finalizar um pedido vazio.");
            return;
        }

        if (total <= 0) {
            System.out.println("O valor total do pedido deve ser maior que zero.");
            return;
        }

        status = StatusDePedido.AGUARDANDO_PAGAMENTO;
        System.out.println("Pedido finalizado com sucesso!");
        System.out.println("Novo status para o pedido: " + status);

        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            itens.add(new ItemPedido(
                    itemCarrinho.getProduto(),
                    itemCarrinho.getQuantidade(),
                    itemCarrinho.getProduto().getValorVenda()
            ));
        }

        notificarCliente("Seu pedido #" + id + " foi finalizado e está aguardando pagamento. Valor total: R$ " + String.format("%.2f", total));
    }

    private void notificarCliente(String mensagem) {
        for (Notificador notificador : notificadores) {
            notificador.notificar(mensagem);
        }
    }

    public void realizarPagamento() {
        if (status != StatusDePedido.AGUARDANDO_PAGAMENTO) {
            System.out.println("O pagamento só pode ser realizado para pedidos aguardando pagamento.");
            System.out.println("Status atual: " + status);
            return;
        }

        status = StatusDePedido.PAGO;
        System.out.println("Pagamento aprovado com sucesso!");
        System.out.println("Novo status para o pedido: " + status);

        notificarCliente("O pagamento do seu pedido #" + id + " foi aprovado com sucesso! Seu pedido será enviado em breve.");
    }

    public void realizarEntrega() {
        if (status != StatusDePedido.PAGO) {
            System.out.println("A entrega só pode ser realizada para pedidos pagos.");
            System.out.println("Status atual: " + status);
            return;
        }

        status = StatusDePedido.ENVIADO;
        System.out.println("Pedido enviado com sucesso!");
        System.out.println("Novo status para o pedido: " + status);

        notificarCliente("Seu pedido #" + id + " foi enviado e está a caminho!");
    }

    public void finalizarEntrega() {
        if (status != StatusDePedido.ENVIADO) {
            System.out.println("A finalização só pode ser realizada para pedidos enviados.");
            System.out.println("Status atual: " + status);
            return;
        }

        status = StatusDePedido.FINALIZADO;
        System.out.println("Entrega finalizada com sucesso!");
        System.out.println("Novo status para o pedido: " + status);

        notificarCliente("Seu pedido #" + id + " foi entregue! Obrigado por comprar conosco!");
    }

    public void alterarQuantidade(Scanner sc) {
        if (!aptoAlterarCarrinho()) {
            return;
        }

        if (itensCarrinho.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }

        System.out.println("\n=== Itens no Carrinho ===");
        for (int i = 0; i < itensCarrinho.size(); i++) {
            ItemCarrinho item = itensCarrinho.get(i);
            System.out.printf("%d. %s - Quantidade: %d - Valor unitário: R$ %.2f%n",
                    item.getProduto().getIdentificador(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getValorVenda());
        }

        System.out.println("\nDigite o código do produto que deseja alterar ou 0 para sair: ");
        int inputCode = sc.nextInt();

        if (inputCode == 0) {
            return;
        }

        boolean itemEncontrado = false;
        for (ItemCarrinho item : itensCarrinho) {
            if (item.getProduto().getIdentificador() == inputCode) {
                System.out.println("Digite a nova quantidade do produto: ");
                int quantidade = sc.nextInt();

                if (quantidade <= 0) {
                    System.out.println("Quantidade inválida. O produto será removido do carrinho.");
                    itensCarrinho.remove(item);
                } else {
                    item.setQuantidade(quantidade);
                    System.out.println("Quantidade atualizada com sucesso!");
                }

                itemEncontrado = true;
                break;
            }
        }

        if (!itemEncontrado) {
            System.out.println("Produto não encontrado no carrinho.");
        }

        total = calcularTotal();
    }

    public boolean aptoAlterarCarrinho() {
        if (status != StatusDePedido.ABERTO) {
            System.out.println("Apenas pedidos em aberto podem ser alterados.");
            System.out.println("Status atual: " + status);
            return false;
        }
        return true;
    }

    public StatusDePedido getStatus() {
        return status;
    }

    public void exibirItensCarrinho() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }

        System.out.println("\n=== Itens no Carrinho ===");
        for (int i = 0; i < itensCarrinho.size(); i++) {
            ItemCarrinho item = itensCarrinho.get(i);
            System.out.printf("%d. %s - Quantidade: %d - Valor unitário: R$ %.2f - Subtotal: R$ %.2f%n",
                    item.getProduto().getIdentificador(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getValorVenda(),
                    item.getQuantidade() * item.getProduto().getValorVenda());
        }

        System.out.printf("Total do carrinho: R$ %.2f%n", total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== DETALHES DO PEDIDO ==========\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Data da criação: ").append(dataCriacao).append("\n");
        sb.append("Cliente: ").append(cliente.getNome()).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Itens:\n");

        if (itensCarrinho.isEmpty()) {
            sb.append("  Nenhum item no carrinho\n");
        } else {
            for (ItemCarrinho item : itensCarrinho) {
                sb.append("  - ").append(item.getProduto().getNome())
                        .append(" | Qtd: ").append(item.getQuantidade())
                        .append(" | Valor unitário: R$ ").append(String.format("%.2f", item.getProduto().getValorVenda()))
                        .append(" | Subtotal: R$ ").append(String.format("%.2f", item.getQuantidade() * item.getProduto().getValorVenda()))
                        .append("\n");
            }
        }

        sb.append("Total: R$ ").append(String.format("%.2f", total)).append("\n");

        return sb.toString();
    }
}