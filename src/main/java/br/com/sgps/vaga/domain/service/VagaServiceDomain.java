package br.com.sgps.vaga.domain.service;

import br.com.sgps.domain.service.DomainService;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.vaga.domain.exception.VagaNaoEncontradaException;
import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@DomainService
@RequiredArgsConstructor
public class VagaServiceDomain {

    private final VagaRepositoryPort vagaRepositoryPort;
    private final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    public Vaga salvar(String titulo, String descricao, LocalDate dataInicio,
                       LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                       InstituicaoId instituicaoId){

        validarInstituicaoExistente(instituicaoId);
        Vaga vaga = Vaga.criarNovaVaga(titulo, descricao, dataInicio,
                dataFim,limiteInscricoes , status, observacao, instituicaoId);
        validarTituloEmUso(vaga.id(),titulo);
        vagaRepositoryPort.persistir(vaga);
        return vaga;
    }
    public Vaga alterar(VagaId id ,String titulo, String descricao, LocalDate dataInicio,
                        LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                        InstituicaoId instituicaoId){

        validarInstituicaoExistente(instituicaoId);
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
        vagaRepositoryPort.persistir(vaga);
        return vaga;
    }


    private void validarTituloEmUso(VagaId id, String titulo) {
        if(vagaRepositoryPort.existeTituloCadastrado(titulo, id)){
            throw new NegocioException("Já existe uma vaga com o título informado.");
        }
    }

    private void validarInstituicaoExistente(InstituicaoId instituicaoId){
        Objects.requireNonNull(instituicaoId);
        if(!instituicaoRepositoryDomain.existe(instituicaoId)){
            throw new NegocioException("Instituição não encontrada com o ID: " + instituicaoId);
        }
    }
}
