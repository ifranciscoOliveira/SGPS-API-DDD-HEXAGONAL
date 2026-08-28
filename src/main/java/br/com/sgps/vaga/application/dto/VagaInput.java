package br.com.sgps.vaga.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public record VagaInput(
        String titulo,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicio,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataFim,
        Integer limiteInscricoes,
        String status,
        String observacao,
        UUID instituicaoId) {
}
