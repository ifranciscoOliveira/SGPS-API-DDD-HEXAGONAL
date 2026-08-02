package br.com.sgps.vaga.application.port.in;

import br.com.sgps.vaga.application.dto.VagaInput;
import br.com.sgps.vaga.application.dto.VagaOutPut;

public interface CadastrarVagaUseCase {

    VagaOutPut cadastrar(VagaInput vagaInput);

}
