package br.com.sgps.inscricao.domain.service;

import br.com.sgps.candidato.application.port.out.CandidatoRepositoryPort;
import br.com.sgps.candidato.domain.entity.Candidato;
import br.com.sgps.inscricao.domain.valueobject.InscricaoId;
import br.com.sgps.inscricao.application.port.out.InscrticaoRepositoryPort;
import br.com.sgps.inscricao.domain.entity.Inscricao;
import br.com.sgps.shared.domain.annotation.DomainService;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.vaga.domain.exception.VagaNaoEncontradaException;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class InscricaoServiceDomain {

    private final VagaRepositoryPort vagaRepositoryPort;
    private final CandidatoRepositoryPort candidatoRepositoryPort;
    private final InscrticaoRepositoryPort inscrticaoRepositoryPort;

    public Inscricao realizarInscricao(UUID candidatoId, UUID vagaId) {

        Candidato candidato = candidatoRepositoryPort.consultarPorId(new CandidatoId(candidatoId))
                .orElseThrow(() -> new IllegalArgumentException("Candidato não encontrado"));
        Vaga vaga = vagaRepositoryPort.buscarPorId(new VagaId(vagaId))
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada"));

        validarCandidatoJaInscritoParaVaga(candidatoId, vagaId);
        Inscricao inscricao = Inscricao.criarNovaInscricao(new CandidatoId(candidato.id().value())
                , new VagaId(vaga.id().value()));

        vaga.validarPeriodoDeInscricaoParaVaga();

        return inscricao;
    }

    public Inscricao avancarProximaEtapaDaInscricao(InscricaoId inscricaoId){
        Inscricao inscricao = inscrticaoRepositoryPort.buscarPorId(inscricaoId.value());

        if (inscricao == null) {
            throw new IllegalArgumentException("Inscrição não encontrada");
        }
        inscricao.alterarParaProximaEtapa();
        return inscricao;
    }

    private void validarCandidatoJaInscritoParaVaga(UUID idCandidao , UUID vagaId) {
        if(inscrticaoRepositoryPort.existeInscricaoPorCandidatoEPorVaga(idCandidao, vagaId)) {
            throw new VagaNaoEncontradaException("Candidato já inscrito para essa vaga.");
        }
    }

}
