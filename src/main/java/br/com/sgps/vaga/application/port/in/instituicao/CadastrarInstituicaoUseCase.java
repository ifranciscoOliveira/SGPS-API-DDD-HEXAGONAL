package br.com.sgps.vaga.application.port.in.instituicao;

import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;

public interface CadastrarInstituicaoUseCase {

    InstituicaoOutPut criar(InstituicaoInput instituicaoInput);
}

