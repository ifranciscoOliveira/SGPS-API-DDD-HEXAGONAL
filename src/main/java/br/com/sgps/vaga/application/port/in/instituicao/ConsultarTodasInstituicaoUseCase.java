package br.com.sgps.vaga.application.port.in.instituicao;

import br.com.sgps.vaga.application.dto.InstituicaoOutPut;

import java.util.List;

public interface ConsultarTodasInstituicaoUseCase {

    List<InstituicaoOutPut> consultarTodos();

}
