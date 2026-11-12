package br.com.senai.deliveryavaliacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.dto.ProdutoResponse;
import br.com.senai.deliveryavaliacao.exeption.RecursoNaoEncontradoException;
import br.com.senai.deliveryavaliacao.exeption.RequisicaoInvalidaException;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public List<ProdutoResponse> listar() {
        return produtoRepository.findAll().stream().map(ProdutoResponse::de).toList();
    }

    public ProdutoResponse buscar(Long id) {
        return ProdutoResponse.de(buscarProduto(id));
    }

    public ProdutoResponse criar(ProdutoRequest dados) {
        Restaurante restaurante = buscarRestaurante(dados.restauranteId());
        Produto produto = new Produto();
        preencher(produto, dados, restaurante);
        return ProdutoResponse.de(produtoRepository.save(produto));
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest dados) {
        Produto produto = buscarProduto(id);
        Restaurante restaurante = buscarRestaurante(dados.restauranteId());
        preencher(produto, dados, restaurante); // o id do produto não é alterado
        return ProdutoResponse.de(produtoRepository.save(produto));
    }

    private void preencher(Produto produto, ProdutoRequest dados, Restaurante restaurante) {
        produto.setNome(dados.nome().trim());
        produto.setPreco(dados.preco());
        produto.setRestaurante(restaurante);
    }

    private Produto buscarProduto(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));
    }

    private Restaurante buscarRestaurante(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RequisicaoInvalidaException("Informe o restaurante existente"));
    }
}