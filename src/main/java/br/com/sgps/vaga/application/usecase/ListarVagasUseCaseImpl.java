package br.com.sgps.vaga.application.usecase;

import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.application.assembler.VagaAssembler;
import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.application.port.in.ListarVagasUseCase;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import org.springframework.stereotype.Service;


@Service
public class ListarVagasUseCaseImpl implements ListarVagasUseCase {

    private final VagaRepositoryPort vagaRepositoryPort;
    private final VagaAssembler vagaAssembler;

    public ListarVagasUseCaseImpl(VagaRepositoryPort vagaRepositoryPort, VagaAssembler vagaAssembler) {
        this.vagaRepositoryPort = vagaRepositoryPort;
        this.vagaAssembler = vagaAssembler;
    }

    @Override
    public Pagina<VagaOutPut> listarVagas(VagaFiltro vagaFiltro, Paginacao paginacao) {
       return vagaRepositoryPort.listar(vagaFiltro, paginacao).map(vagaAssembler::domainToOutPut);
    }
}
