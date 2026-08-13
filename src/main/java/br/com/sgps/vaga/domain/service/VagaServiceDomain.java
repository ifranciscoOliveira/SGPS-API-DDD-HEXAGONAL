package br.com.sgps.vaga.domain.service;

import br.com.sgps.shared.domain.annotation.DomainService;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.shared.domain.exception.NegocioException;
import br.com.sgps.vaga.domain.exception.VagaNaoEncontradaException;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@DomainService
@RequiredArgsConstructor
public class VagaServiceDomain {

    private final VagaRepositoryPort vagaRepositoryPort;
    private final InstituicaoRepositoryPort instituicaoRepositoryPort;


    public Vaga salvar(String titulo, String descricao, LocalDate dataInicio,
                       LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                       InstituicaoId instituicaoId){

        existeInstituicao(instituicaoId);
        Vaga vaga = Vaga.criarNovaVaga(titulo, descricao, dataInicio,
                dataFim,limiteInscricoes , status, observacao, instituicaoId);
        validarTituloEmUso(vaga.id(),titulo);
        return vaga;
    }
    public Vaga alterar(VagaId id ,String titulo, String descricao, LocalDate dataInicio,
                        LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                        InstituicaoId instituicaoId){

        Vaga vaga = vagaRepositoryPort.buscarPorId(id).orElseThrow(()->
                new VagaNaoEncontradaException("Vaga não encontrada com o ID: " + id));

        validarTituloEmUso(id, titulo);
        vaga.alterarObservacao(observacao);
        vaga.alterarTitulo(titulo);
        vaga.alterarStatus(status);
        vaga.alterarDataFim(dataFim);
        vaga.alterarDataInicio(dataInicio);
        vaga.alterarLimiteInscricoes(limiteInscricoes);
        vaga.alterarDescricao(descricao);
        vaga.alterarInstituicaoId(instituicaoId);
        return vaga;
    }


    private void validarTituloEmUso(VagaId id, String titulo) {
        if(vagaRepositoryPort.existeTituloCadastrado(titulo, id)){
            throw new NegocioException("Já existe uma vaga com o título informado.");
        }
    }
    private void existeInstituicao(InstituicaoId instituicaoId){
        Objects.requireNonNull(instituicaoId);
        if(!instituicaoRepositoryPort.existe(instituicaoId)){
            throw new NegocioException("Instituição não encontrada com o ID: " + instituicaoId);
        }
    }

}
