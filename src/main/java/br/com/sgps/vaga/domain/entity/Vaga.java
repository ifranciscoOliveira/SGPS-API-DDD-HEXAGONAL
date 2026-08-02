package br.com.sgps.vaga.domain.entity;

import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Objects;

public class Vaga {

    private VagaId id;
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer limiteInscricoes;
    private String status;
    private String observacao;
    private InstituicaoId instituicaoId;

    @Builder(builderClassName = "VagaExistenteBuild",builderMethodName = "criarExistente")
    public Vaga(VagaId id, String titulo, String descricao, LocalDate dataInicio,
                LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                InstituicaoId instituicaoId) {
        this.limiteInscricoes = limiteInscricoes;
        setId(id);
        alterarTitulo(titulo);
        alterarDescricao(descricao);
        alterarDataInicio(dataInicio);
        alterarDataFim(dataFim);
        alterarStatus(status);
        alterarObservacao(observacao);
        alterarInstituicaoId(instituicaoId);
    }

    public static Vaga criarNovaVaga(String titulo, String descricao, LocalDate dataInicio,
                LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                InstituicaoId instituicaoId) {
        return new Vaga(new VagaId(), titulo, descricao, dataInicio,
                dataFim,limiteInscricoes, status, observacao, instituicaoId);

    }

    public VagaId id() {
        return id;
    }

    public String titulo() {
        return titulo;
    }

    public String descricao() {
        return descricao;
    }

    public LocalDate dataInicio() {
        return dataInicio;
    }

    public LocalDate dataFim() {
        return dataFim;
    }

    public String status() {
        return status;
    }

    public String observacao() {
        return observacao;
    }

    public InstituicaoId instituicaoId() {
        return instituicaoId;
    }

    public void alterarTitulo(String titulo) {
        Objects.requireNonNull(titulo);
        this.titulo = titulo;
    }

    public void alterarDescricao(String descricao) {
        Objects.requireNonNull(descricao);
        this.descricao = descricao;
    }

    public void alterarDataInicio(LocalDate dataInicio) {
        Objects.requireNonNull(dataInicio);
        validarPeriodoInicioFim(dataInicio, this.dataFim);
        this.dataInicio = dataInicio;
    }

    public void alterarDataFim(LocalDate dataFim) {
        Objects.requireNonNull(dataFim);
        validarPeriodoInicioFim(this.dataInicio, dataFim);
        this.dataFim = dataFim;
    }

    public void alterarLimiteInscricoes(Integer limiteInscricoes) {
        Objects.requireNonNull(limiteInscricoes);
        this.limiteInscricoes  = limiteInscricoes;
    }

    public void alterarStatus(String status) {
        Objects.requireNonNull(status);
        this.status = status;
    }

    public void alterarObservacao(String observacao) {
        Objects.requireNonNull(observacao);
        this.observacao = observacao;
    }
    public void alterarInstituicaoId(InstituicaoId instituicaoId) {
        Objects.requireNonNull(instituicaoId);
        this.instituicaoId = instituicaoId;
    }

    public Integer limiteInscricoes() {
        return limiteInscricoes;
    }

    public void validarPeriodoDeInscricaoParaVaga() {
        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isBefore(this.dataInicio) || dataAtual.isAfter(this.dataFim)) {
            throw new NegocioException("Inscrições não estão abertas para esta vaga.");
        }
    }

    private void validarPeriodoInicioFim(LocalDate dataIncial, LocalDate dataFinal){
        if(dataIncial != null && dataFinal != null) {
            if (dataIncial.isAfter(dataFinal) ||  dataIncial.isEqual(dataFinal)) {
                throw new NegocioException("Período Inválido");
            }
        }
    }


    private void setId(VagaId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }



}
