package br.com.sgps.vaga.application.port.out;

import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.vaga.domain.valueobject.VagaId;

import java.util.List;
import java.util.Optional;

public interface VagaRepositoryPort {

    Vaga persistir(Vaga vaga);

    List<Vaga> consultarTodos();

    Pagina<Vaga> listar(VagaFiltro filtro, Paginacao paginacao);

    Optional<Vaga> buscarPorId(VagaId id);

    void remover(VagaId id);

    boolean existeTituloCadastrado(String titulo, VagaId id);

    boolean existe(VagaId id);
}
