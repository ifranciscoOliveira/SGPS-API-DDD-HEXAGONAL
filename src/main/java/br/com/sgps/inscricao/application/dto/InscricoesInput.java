package br.com.sgps.inscricao.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@Getter
@Setter
public class InscricoesInput {

    private List<UUID> idsInscricoes;
}
