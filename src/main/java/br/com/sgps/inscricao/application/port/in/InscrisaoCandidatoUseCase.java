package br.com.sgps.inscricao.application.port.in;

import java.util.UUID;

public interface InscrisaoCandidatoUseCase {

    void inscreverCandidatoEmVaga(UUID candidatoId, UUID vagaId);

    }
