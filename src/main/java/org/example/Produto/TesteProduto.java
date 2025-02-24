package org.example.Produto;

public class TesteProduto { // Apagar essa classe depois - apenas teste
    public static void executarTeste() {
        Produto produto = new Produto(
                "001",
                "Notebook",
                "Eletrônicos",
                2500.00,
                2700.00,
                200.00);
        System.out.println("--- Produto Criado ---");
        produto.listarProduto();

        System.out.println("\n--- Atualizando Produto ---");
        produto.atualizarProduto(
                "Notebook Gamer",
                "Eletrônicos",
                2600.00,
                2800.00,
                150.00);
        produto.listarProduto();
    }
}
