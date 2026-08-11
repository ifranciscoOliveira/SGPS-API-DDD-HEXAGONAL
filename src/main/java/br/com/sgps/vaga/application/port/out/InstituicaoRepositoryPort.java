package br.com.sgps.vaga.application.port.out;

import br.com.sgps.vaga.application.filter.InstituicaoFiltro;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;

import java.util.List;
import java.util.Optional;

public interface InstituicaoRepositoryPort {

    boolean existeDocumentoCadastrado(Documento documento, InstituicaoId id);

    boolean existeEmailCadastrado(Email email, InstituicaoId id);

    boolean existe(InstituicaoId id);

    void persistir(Instituicao instituicao);

    Optional<Instituicao> consultarPorId(InstituicaoId id);

    List<Instituicao> listarTodos();

    Pagina<Instituicao> buscar(InstituicaoFiltro instituicaoFiltro, Paginacao paginacao);
}
