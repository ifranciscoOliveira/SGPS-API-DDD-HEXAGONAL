package br.com.sgps.vaga.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record VagaInput(
        String titulo,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        Integer limiteInscricoes,
        String status,
        String observacao,
        UUID instituicaoId) {
}
