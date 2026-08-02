package br.com.sgps.application.vaga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarVagaInput {

    private Long id;
    private String titulo;
    private String descricao;
    private java.time.LocalDate dataInicio;
    private java.time.LocalDate dataFim;
    private Integer limiteInscricoes;
    private String status;
    private String observacao;
    private UUID instituicaoId;
}
