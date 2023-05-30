package br.com.fiap.petshop2si.dominio.pessoa.entity;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PessoaJuridica extends Pessoa {

    private String CNPJ;

    private String inscricaoEstadual;

   private Set<Pessoa> socios = new LinkedHashSet<>();


    public PessoaJuridica addSocio(Pessoa p) {
        if (this.equals(p)) throw new RuntimeException("Não é permitido adicionar-se");
        socios.add(p);
        return this;
    }

    public PessoaJuridica remove(Pessoa p) {
        socios.remove(p);
        return this;
    }

    public Set<Pessoa> getSocios() {
        return Collections.unmodifiableSet(socios);
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public PessoaJuridica setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
        return this;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public PessoaJuridica setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
        return this;
    }
}
