package br.com.sgps.vaga.application.port.in.instituicao;

import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;

public interface AtualizarInstituicaoUseCase {

    InstituicaoOutPut alterar(InstituicaoId id, InstituicaoInput instituicaoInput);
}
