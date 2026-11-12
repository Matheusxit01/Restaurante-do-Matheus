package br.com.senai.deliveryavaliacao.exeption;

import java.util.Map;

public record ErroResposta(int status, String mensagem, Map<String, String> campos) {
}