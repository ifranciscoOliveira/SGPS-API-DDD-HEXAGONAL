package br.com.sgps.vaga.application.usecase.instituicao;

import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.port.in.instituicao.ConsultarTodasInstituicaoUseCase;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarTodasInstituicaoUseCaseImpl implements ConsultarTodasInstituicaoUseCase {


    private final InstituicaoRepositoryPort instituicaoRepositoryPort;

    public ConsultarTodasInstituicaoUseCaseImpl(InstituicaoRepositoryPort instituicaoRepositoryPort) {
        this.instituicaoRepositoryPort = instituicaoRepositoryPort;
    }


    @Override
    public List<InstituicaoOutPut> consultarTodos() {
        var instituicoes = instituicaoRepositoryPort.listarTodos();
        return instituicoes.stream().map(InstituicaoOutPut::new).toList();
    }
}
