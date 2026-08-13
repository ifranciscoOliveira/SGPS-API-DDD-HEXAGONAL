package br.com.sgps.candidato.domain.entity;

import br.com.sgps.shared.domain.exception.NegocioException;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import lombok.Builder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Candidato {


    private CandidatoId id;
    private Documento cpf;
    private String nome;
    private Email email;
    private String telefone;
    private LocalDate dataNascimento;

    @Builder(builderClassName = "CandidadoExistenteBuild",builderMethodName = "criarExistente")
    public Candidato(CandidatoId id, Documento cpf, String nome,
                     Email email, String telefone, LocalDate dataNascimento) throws NegocioException {
       this.setId(id);
       this.setCpf(cpf);
       this.alterarNome(nome);
       this.alterarEmail(email);
       this.alterarTelefone(telefone);
       this.alterarDataNascimento(dataNascimento);
    }

    public static Candidato criarNovoCandidato(Documento cpf, String nome,
                                                Email email, String telefone, LocalDate dataNascimento) throws NegocioException {
        return new Candidato(
                new CandidatoId(),
                cpf,
                nome,
                email,
                telefone,
                dataNascimento);

    }

    public CandidatoId id() {
        return id;
    }

    public Documento cpf() {
        return cpf;
    }

    public String nome() {
        return nome;
    }

    public Email email() {
        return email;
    }

    public String telefone() {
        return telefone;
    }

    public LocalDate dataNascimento() {
        return dataNascimento;
    }

    public void alterarNome(String novoNome) {
        Objects.requireNonNull(novoNome);
        if (novoNome.equalsIgnoreCase(this.nome)) {
            return;
        }

        this.nome = novoNome;
    }
    public void alterarTelefone(String novoTelefone) {
        Objects.requireNonNull(novoTelefone);
        if (novoTelefone.equalsIgnoreCase(this.telefone)) {
            return;
        }
        this.telefone = novoTelefone;
    }
    public void alterarDataNascimento(LocalDate novaDataNascimento){
        Objects.requireNonNull(novaDataNascimento);
        validarMaiorDeIdade(novaDataNascimento);
        this.dataNascimento = novaDataNascimento;
    }

    public void alterarEmail(Email novoEmail) {
        Objects.requireNonNull(novoEmail);

        if (novoEmail.equals(this.email)) {
            return;
        }

        this.email = novoEmail;
    }

    private void setId(CandidatoId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setCpf(Documento cpf) {
        Objects.requireNonNull(cpf);
        this.cpf = cpf;
    }


    private void validarMaiorDeIdade(LocalDate dataNascimento) throws NegocioException {
        LocalDate hoje = LocalDate.now();
        long idade = ChronoUnit.YEARS.between(dataNascimento, hoje);

        if(idade < 18){
            throw new NegocioException("Condidato deve ser maior de idade");
        }
    }
}
