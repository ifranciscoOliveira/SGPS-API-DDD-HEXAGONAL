package br.com.sgps.vaga.application.usecase.instituicao;

import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.filter.InstituicaoFiltro;
import br.com.sgps.vaga.application.port.in.instituicao.ListarInstituicaoUseCase;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ListarInstituicaoUseCaseImpl implements ListarInstituicaoUseCase {

    private final InstituicaoRepositoryPort instituicaoRepositoryPort;

    public ListarInstituicaoUseCaseImpl(InstituicaoRepositoryPort instituicaoRepositoryPort) {
        this.instituicaoRepositoryPort = instituicaoRepositoryPort;
    }

    @Override
    public Pagina<InstituicaoOutPut> buscar(InstituicaoFiltro instituicaoFiltro, Paginacao paginacao) {
        return instituicaoRepositoryPort.buscar(instituicaoFiltro, paginacao)
                .map(InstituicaoOutPut::new);
    }
}
