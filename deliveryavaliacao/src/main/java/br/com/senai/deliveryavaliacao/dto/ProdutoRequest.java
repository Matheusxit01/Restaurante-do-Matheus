package br.com.senai.deliveryavaliacao.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequest(

        @NotBlank(message = "Informe o nome do produto")
        String nome,

        @NotNull(message = "O preço deve ser maior que zero")
        @DecimalMin(value = "0.00", inclusive = false, message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "Informe o restaurante existente")
        Long restauranteId) {
}