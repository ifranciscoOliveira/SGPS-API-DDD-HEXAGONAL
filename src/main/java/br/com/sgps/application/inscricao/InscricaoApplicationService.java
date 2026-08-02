package br.com.sgps.application.inscricao;


import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.vaga.domain.exception.VagaNaoEncontradaException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.repository.InscricaoRepositoryDomain;
import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.domain.service.InscricaoServiceDomain;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InscricaoApplicationService {

    private final InscricaoServiceDomain inscricaoServiceDomain;
    private final InscricaoRepositoryDomain inscricaoRepositoryDomain;
    private final VagaRepositoryDomain vagaRepositoryDomain;
    private  final CandidatoRepositoryDomain candidatoRepositoryDomain;

    @Transactional
    public void inscreverCandidatoEmVaga(UUID candidatoId, UUID vagaId) {

        Vaga vaga = vagaRepositoryDomain.consultarPorId(new VagaId(vagaId))
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada"));
       Candidato candidato = candidatoRepositoryDomain.consultarPorId(new CandidatoId(candidatoId))
                .orElseThrow(() -> new IllegalArgumentException("Candidato não encontrado"));

        validarCandidatoJaInscritoParaVaga(candidatoId, vagaId);

        var inscricao = inscricaoServiceDomain.realizarInscricao(vaga,candidato);

        inscricaoRepositoryDomain.persistir(inscricao);
    }

    private void validarCandidatoJaInscritoParaVaga(UUID idCandidao , UUID vagaId) {
        if(inscricaoRepositoryDomain.existeInscricaoPorCandidatoEPorVaga(idCandidao, vagaId)) {
            throw new VagaNaoEncontradaException("Candidato já inscrito para essa vaga.");
        }
    }

    @Transactional
    public void avancarProximaEtapaDaInscricao(UUID inscricaoId) {
        var inscricao = inscricaoRepositoryDomain.consultarPorId(inscricaoId)
                .orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada"));

        inscricao.alterarParaProximaEtapa();

        inscricaoRepositoryDomain.persistir(inscricao);
    }


}
