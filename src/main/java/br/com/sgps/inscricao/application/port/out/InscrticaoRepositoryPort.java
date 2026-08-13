package br.com.sgps.inscricao.application.port.out;

import br.com.sgps.inscricao.domain.entity.Inscricao;

import java.util.UUID;

public interface InscrticaoRepositoryPort {

    void persistir(Inscricao inscricao);

    boolean existeInscricaoPorCandidatoEPorVaga(UUID candidatoId, UUID vagaId);

    Inscricao buscarPorId(UUID inscricaoId);

}