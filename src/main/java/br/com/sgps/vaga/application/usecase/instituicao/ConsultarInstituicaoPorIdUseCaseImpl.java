package br.com.sgps.vaga.application.usecase.instituicao;

import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.port.in.instituicao.ConsultarInstituicaoPorIdUseCase;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;

public class ConsultarInstituicaoPorIdUseCaseImpl  implements ConsultarInstituicaoPorIdUseCase {

    private final InstituicaoRepositoryPort instituicaoRepositoryPort;

    public ConsultarInstituicaoPorIdUseCaseImpl(InstituicaoRepositoryPort instituicaoRepositoryPort) {
        this.instituicaoRepositoryPort = instituicaoRepositoryPort;
    }

    @Override
    public InstituicaoOutPut conusltarPorID(InstituicaoId id) {
        var instituicao = instituicaoRepositoryPort.consultarPorId(id).orElse(null);
        if(instituicao != null){
            return new InstituicaoOutPut(instituicao);
        }
        return new InstituicaoOutPut();
    }
}
