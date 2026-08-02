package br.com.sgps.vaga.application.filter;

import java.time.LocalDate;

public record VagaFiltro(String  titulo, String descricao, LocalDate dataInicio, LocalDate dataFim, Integer limiteInscricoes, String status, String observacao, String instituicaoId) {
}
