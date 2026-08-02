package br.com.sgps.domain.repository;

import br.com.sgps.application.instituicao.InstituicaoFiltro;
import br.com.sgps.common.pagination.Pagina;
import br.com.sgps.common.pagination.Paginacao;
import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;

import java.util.List;
import java.util.Optional;

public interface InstituicaoRepositoryDomain {

    public boolean exiteDocumentoCadastrado(Documento documento, InstituicaoId id);

    boolean existeEmailCadastrado(Email email, InstituicaoId id);

    boolean existe(InstituicaoId id);

    void persistir(Instituicao instituicao);

    Optional<Instituicao> conusltarPorId(InstituicaoId id);

    List<Instituicao> listarTdos();

    Pagina<Instituicao> buscar(InstituicaoFiltro instituicaoFiltro, Paginacao paginacao);

}
