package br.com.sgps.vaga.application.usecase;

import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.application.port.in.ConsultarTodasVagasUseCase;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarTodasVagasUseCaseImpl implements ConsultarTodasVagasUseCase {

    private final VagaRepositoryPort vagaRepositoryPort;

    public ConsultarTodasVagasUseCaseImpl(VagaRepositoryPort vagaRepositoryPort) {
        this.vagaRepositoryPort = vagaRepositoryPort;
    }

    @Override
    public List<VagaOutPut> consultarTodasVagas() {
        return vagaRepositoryPort.consultarTodos().stream()
                .map(VagaOutPut::from)
                .toList();
    }

}
