package br.com.senai.deliveryavaliacao.dto;

import java.math.BigDecimal;

import br.com.senai.deliveryavaliacao.model.Produto;

public record ProdutoResponse(Long id, String nome, BigDecimal preco, Long restauranteId) {

    public static ProdutoResponse de(Produto p) {
        return new ProdutoResponse(
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getRestaurante().getId());
    }
}