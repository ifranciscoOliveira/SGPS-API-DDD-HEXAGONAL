package br.com.sgps.application.instituicao;

import br.com.sgps.common.pagination.Pagina;
import br.com.sgps.common.pagination.Paginacao;
import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.vaga.domain.exception.InstituicaoNaoEncontradoException;
import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.vaga.domain.service.InstituicaoService;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InstituicaoManagementApplicationService {

    public final InstituicaoService instituicaoServiceDomain;
    public final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    @Transactional
    public Instituicao criar(InstituicaoInput instituicaoInput){
        Objects.requireNonNull(instituicaoInput);

        var instituicao = instituicaoServiceDomain.salvar(instituicaoInput.getNome(),
                new Documento(instituicaoInput.getCnpjCpf()),
                instituicaoInput.getTelefone(),
                new Email(instituicaoInput.getEmail()));


        instituicaoRepositoryDomain.persistir(instituicao);

        return instituicao;
    }

    @Transactional
    public Instituicao alterar(InstituicaoId id, InstituicaoInput instituicaoInput){

        Objects.requireNonNull(id);
        Objects.requireNonNull(instituicaoInput);
        var instituicaoAlterar = instituicaoServiceDomain.alterar(id,
                instituicaoInput.getNome(),
                new Documento(instituicaoInput.getCnpjCpf()),
                instituicaoInput.getTelefone(),
                new Email(instituicaoInput.getEmail()));

        instituicaoRepositoryDomain.persistir(instituicaoAlterar);

        return instituicaoAlterar;
    }


    public Instituicao conusltarPorID(InstituicaoId id){
        return instituicaoRepositoryDomain.conusltarPorId(id).orElseThrow(InstituicaoNaoEncontradoException::new);
    }

    public List<Instituicao> consultarTodos(){
        return instituicaoRepositoryDomain.listarTdos();
    }

    public Pagina<InstituicaoOutPut> buscar(InstituicaoFiltro instituicaoFiltro, Paginacao paginacao){
        return instituicaoRepositoryDomain.buscar(instituicaoFiltro, paginacao)
                .map(instituicao -> new InstituicaoOutPut(instituicao));
    }
}
