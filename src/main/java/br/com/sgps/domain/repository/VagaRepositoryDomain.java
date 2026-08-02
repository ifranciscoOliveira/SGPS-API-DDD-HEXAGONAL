package br.com.sgps.domain.repository;

import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.common.pagination.Pagina;
import br.com.sgps.common.pagination.Paginacao;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.vaga.domain.valueobject.VagaId;

import java.util.List;
import java.util.Optional;

public interface VagaRepositoryDomain {

    boolean existeTituloCadastrado(String titulo, VagaId id);

    boolean existe(VagaId id);

    Vaga persistir(Vaga vaga);

    Optional<Vaga> consultarPorId(VagaId id);

    List<Vaga> consultarTodos();

    Pagina<Vaga> listar(VagaFiltro vagaFiltro, Paginacao paginacao);
}
