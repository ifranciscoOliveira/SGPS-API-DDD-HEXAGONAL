package br.com.sgps.vaga.application.port.in;

import br.com.sgps.vaga.application.dto.VagaInput;
import br.com.sgps.vaga.application.dto.VagaOutPut;

import java.util.UUID;

public interface AtualizarVagaUseCase {

    VagaOutPut alterar(UUID idu, VagaInput vagaInput);
}
