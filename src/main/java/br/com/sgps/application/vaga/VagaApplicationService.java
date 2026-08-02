package br.com.sgps.application.vaga;

import br.com.sgps.common.pagination.Pagina;
import br.com.sgps.common.pagination.Paginacao;
import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.vaga.domain.service.VagaServiceDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VagaApplicationService {

    private final VagaServiceDomain vagaServiceDomain;
    private final VagaRepositoryDomain vagaRepositoryDomain;


    @Transactional
    public Vaga criar(VagaInputOud vagaInput){
        var vaga = vagaServiceDomain.salvar(vagaInput.getTitulo(),
                vagaInput.getDescricao(),
                vagaInput.getDataInicio(),
                vagaInput.getDataFim(),
                vagaInput.getLimiteInscricoes(),
                vagaInput.getStatus(),
                vagaInput.getObservacao(),
                new InstituicaoId(vagaInput.getInstituicaoId())
        );

        return vagaRepositoryDomain.persistir(vaga);
    }

    @Transactional
    public Vaga alterar(VagaId id, AtualizarVagaInput vagaAlterarInput){
        var vagaAlterar = vagaServiceDomain.alterar(
                id,
                vagaAlterarInput.getTitulo(),
                vagaAlterarInput.getDescricao(),
                vagaAlterarInput.getDataInicio(),
                vagaAlterarInput.getDataFim(),
                vagaAlterarInput.getLimiteInscricoes(),
                vagaAlterarInput.getStatus(),
                vagaAlterarInput.getObservacao(),
                new InstituicaoId(vagaAlterarInput.getInstituicaoId()));
        return vagaRepositoryDomain.persistir(vagaAlterar);
    }

    public List<Vaga> consultarTodos(){
        return vagaRepositoryDomain.consultarTodos();
    }

    public Pagina<Vaga> listar(VagaFiltro vagaFiltro, Paginacao paginacao){
        return vagaRepositoryDomain.listar(vagaFiltro, paginacao);
    }
}
