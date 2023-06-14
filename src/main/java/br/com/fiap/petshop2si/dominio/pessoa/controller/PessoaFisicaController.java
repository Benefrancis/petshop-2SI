package br.com.fiap.petshop2si.dominio.pessoa.controller;


import br.com.fiap.petshop2si.dominio.pessoa.entity.PessoaFisica;
import br.com.fiap.petshop2si.dominio.pessoa.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
        return pessoaFisica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PessoaFisica> save(@RequestBody PessoaFisica p, UriComponentsBuilder uriBuilder) {
        PessoaFisica saved = repo.save(p);
        if (saved != null) {
            URI uri = uriBuilder.path("/pf/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<PessoaFisica> update(@PathVariable Long id, @RequestBody PessoaFisica p) {

        Optional<PessoaFisica> pessoaFisica = repo.findById(id);

        if (pessoaFisica.isPresent()) {
            PessoaFisica ret = pessoaFisica.get();
            if (Objects.nonNull(p.getNome())) ret.setNome(p.getNome());
            if (Objects.nonNull(p.getRG())) ret.setRG(p.getRG());
            if (Objects.nonNull(p.getCPF())) ret.setCPF(p.getCPF());
            return ResponseEntity.ok(ret);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<PessoaFisica> pessoaFisica = repo.findById(id);
        if (pessoaFisica.isPresent()) {
            repo.delete(pessoaFisica.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
