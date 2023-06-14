package br.com.fiap.petshop2si.dominio.pessoa.controller;


import br.com.fiap.petshop2si.dominio.pessoa.entity.PessoaJuridica;
import br.com.fiap.petshop2si.dominio.pessoa.repository.PessoaJuridicaRepository;
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
@RequestMapping(value = "/pj")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository repo;

    @GetMapping
    public ResponseEntity<Collection<PessoaJuridica>> findAll() {
        List<PessoaJuridica> pessoas = repo.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<PessoaJuridica> findById(@PathVariable Long id) {
        Optional<PessoaJuridica> pessoaJuridica = repo.findById(id);
        return pessoaJuridica.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PessoaJuridica> save(@RequestBody PessoaJuridica p, UriComponentsBuilder uriBuilder) {
        PessoaJuridica saved = repo.save(p);
        if (saved != null) {
            URI uri = uriBuilder.path("/pj/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody PessoaJuridica p) {

        Optional<PessoaJuridica> pessoaJuridica = repo.findById(id);

        if (pessoaJuridica.isPresent()) {
            PessoaJuridica ret = pessoaJuridica.get();

            if (Objects.nonNull(p.getNome())) ret.setNome(p.getNome());
            if (Objects.nonNull(p.getCNPJ())) ret.setCNPJ(p.getCNPJ());
            if (Objects.nonNull(p.getInscricaoEstadual())) ret.setInscricaoEstadual(p.getInscricaoEstadual());

            return ResponseEntity.ok(ret);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<PessoaJuridica> pessoaJuridica = repo.findById(id);
        if (pessoaJuridica.isPresent()) {
            repo.delete(pessoaJuridica.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
