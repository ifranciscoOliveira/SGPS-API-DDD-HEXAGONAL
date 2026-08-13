package br.com.sgps.vaga.domain.entity;

import br.com.sgps.shared.domain.validator.FieldValidations;
import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import lombok.Builder;

import java.util.Objects;

public class Instituicao {

    private InstituicaoId id;
    private String nome;
    private Documento cnpjCpf;
    private String telefone;
    private Email email;

    @Builder(builderClassName = "InstituicaoExistenteBuild", builderMethodName = "criarExistente")
    public Instituicao(InstituicaoId id, String nome, Documento cnpjCpf,
                       String telefone, Email email) {
        setId(id);
        setCnpjCpf(cnpjCpf);
        alterarNome(nome);
        alterarTelefone(telefone);
        alterarEmail(email);
    }

    public static Instituicao criarNovaInstituicao(String nome, Documento cnpjCpf,
                                                   String telefone, Email email){
        return new Instituicao(new InstituicaoId(),
                nome,
                cnpjCpf,
                telefone,
                email);
    }

    public InstituicaoId id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public Documento cnpjCpf() {
        return cnpjCpf;
    }

    public String telefone() {
        return telefone;
    }

    public Email email() {
        return email;
    }

    public void alterarTelefone(String telefone) {
        Objects.requireNonNull(telefone);
        this.telefone = telefone;
    }

    public void alterarEmail(Email email) {
        Objects.requireNonNull(email);
        this.email = email;
    }

    public void alterarNome(String nome) {
        FieldValidations.requiresNonBlank(nome);
        this.nome = nome;
    }

    private void setId(InstituicaoId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setCnpjCpf(Documento cnpjCpf) {
        Objects.requireNonNull(cnpjCpf);
        this.cnpjCpf = cnpjCpf;
    }





}
