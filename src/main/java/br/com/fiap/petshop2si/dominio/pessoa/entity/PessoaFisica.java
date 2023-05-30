package br.com.fiap.petshop2si.dominio.pessoa.entity;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_PESSOA_FISICA")
@DiscriminatorValue("PF")
public class PessoaFisica extends Pessoa {

    @Column(name = "NR_CPF")
    private String CPF;

    @Column(name = "NR_RG")
    private String RG;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_DEPENDENTES",
            joinColumns = {
                    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_PE_DEPENDENTE"))
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ID_DEPENDENTE", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_DEPE_PESSOA"))
            }
    )
    private Set<PessoaFisica> dependentes = new LinkedHashSet<>();


    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String nome, String CPF, String RG, Set<PessoaFisica> dependentes) {
        super(id, nome);
        this.CPF = CPF;
        this.RG = RG;
        this.dependentes = dependentes;
    }

    public PessoaFisica addDependente(PessoaFisica p) {
        if (p.equals(this)) {
            throw new RuntimeException("Não posso me adicionar aqui");
        } else {
            dependentes.add(p);
        }
        return this;
    }

    public PessoaFisica removeDependente(PessoaFisica p) {
        dependentes.remove(p);
        return this;
    }

    public Set<PessoaFisica> getDependentes() {
        return Collections.unmodifiableSet(dependentes);
    }

    public String getCPF() {
        return CPF;
    }

    public PessoaFisica setCPF(String CPF) {
        this.CPF = CPF;
        return this;
    }

    public String getRG() {
        return RG;
    }

    public PessoaFisica setRG(String RG) {
        this.RG = RG;
        return this;
    }


    @Override
    public String toString() {
        return "PessoaFisica{" +
                "CPF='" + CPF + '\'' +
                ", RG='" + RG + '\'' +
                ", dependentes=" + dependentes +
                "} " + super.toString();
    }
}
