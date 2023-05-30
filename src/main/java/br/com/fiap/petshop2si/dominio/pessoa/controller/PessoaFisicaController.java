package br.com.fiap.petshop2si.dominio.pessoa.controller;


import br.com.fiap.petshop2si.dominio.pessoa.entity.PessoaFisica;
import br.com.fiap.petshop2si.dominio.pessoa.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/pf"})
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository repo;

    @GetMapping
    public ResponseEntity<Collection<PessoaFisica>> findAll() {
        List<PessoaFisica> pessoas = repo.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<PessoaFisica> findById(@PathVariable Long id) {
        Optional<PessoaFisica> pessoaFisica = repo.findById(id);
        if (pessoaFisica.isPresent()) return ResponseEntity.ok(pessoaFisica.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PessoaFisica> save(@RequestBody PessoaFisica p, UriComponentsBuilder uriBuilder) {
        PessoaFisica saved = repo.save(p);
        if (saved != null) {
            URI uri = uriBuilder.path("/pf/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }


}
