package br.com.fiap.petshop2si.dominio.pessoa.controller;


import br.com.fiap.petshop2si.dominio.pessoa.entity.PessoaFisica;
import br.com.fiap.petshop2si.dominio.pessoa.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/pf")
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository repo;

    @GetMapping
    public ResponseEntity<Collection<PessoaFisica>> findAll() {
        List<PessoaFisica> pessoas = repo.findAll();
        return ResponseEntity.ok(pessoas);
    }

}
