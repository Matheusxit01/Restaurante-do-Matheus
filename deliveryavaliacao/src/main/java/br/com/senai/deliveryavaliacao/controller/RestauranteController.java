package br.com.senai.deliveryavaliacao.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository repository;

    public RestauranteController(RestauranteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Restaurante> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Restaurante> criar(@RequestBody Restaurante restaurante) {
        restaurante.setId(null);
        return ResponseEntity.status(201).body(repository.save(restaurante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id,
                                                 @RequestBody Restaurante dados) {
        return repository.findById(id)
                .map(r -> {
                    r.setNome(dados.getNome());
                    r.setEndereco(dados.getEndereco());
                    return ResponseEntity.ok(repository.save(r));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}