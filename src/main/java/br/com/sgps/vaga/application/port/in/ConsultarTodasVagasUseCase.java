package br.com.sgps.vaga.application.port.in;

import br.com.sgps.vaga.application.dto.VagaOutPut;

import java.util.List;

public interface ConsultarTodasVagasUseCase {

    public List<VagaOutPut> consultarTodasVagas();
}
