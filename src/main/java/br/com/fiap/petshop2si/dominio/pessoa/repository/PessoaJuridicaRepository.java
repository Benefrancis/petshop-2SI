package br.com.fiap.petshop2si.dominio.pessoa.repository;

import br.com.fiap.petshop2si.dominio.pessoa.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
}
