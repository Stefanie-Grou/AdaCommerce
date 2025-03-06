package org.example.Pedido;

import org.example.Carrinho.ItemCarrinho;

import java.util.ArrayList;
import java.util.List;

public class IniciadorDoCarrinho {
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }
}
