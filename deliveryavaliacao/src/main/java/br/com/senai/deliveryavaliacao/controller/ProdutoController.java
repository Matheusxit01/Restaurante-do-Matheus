package br.com.senai.deliveryavaliacao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.dto.ProdutoResponse;
import br.com.senai.deliveryavaliacao.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(@Valid @RequestBody ProdutoRequest dados) {
        return service.criar(dados);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable Long id,
                                     @Valid @RequestBody ProdutoRequest dados) {
        return service.atualizar(id, dados);
    }
}