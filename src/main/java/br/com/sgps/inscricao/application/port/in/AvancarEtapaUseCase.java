package br.com.sgps.inscricao.application.port.in;

import java.util.UUID;

public interface AvancarEtapaUseCase {

    void avancarEtapa(UUID idInscricao);
}
