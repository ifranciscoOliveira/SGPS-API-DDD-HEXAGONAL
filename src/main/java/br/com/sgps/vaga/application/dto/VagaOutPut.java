package br.com.sgps.vaga.application.dto;

import br.com.sgps.vaga.domain.entity.Vaga;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record VagaOutPut(
        UUID id,
        String titulo,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        Integer limiteInscricoes,
        String status,
        String observacao,
        UUID instituicaoId
) {

    public static VagaOutPut from(Vaga vaga) {
        return new VagaOutPut(
                vaga.id().value(),
                vaga.titulo(),
                vaga.descricao(),
                vaga.dataInicio(),
                vaga.dataFim(),
                vaga.limiteInscricoes(),
                vaga.status(),
                vaga.observacao(),
                vaga.instituicaoId().value()
        );
    }
}
