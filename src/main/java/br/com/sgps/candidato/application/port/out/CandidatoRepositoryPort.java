package br.com.sgps.candidato.application.port.out;

import br.com.sgps.candidato.application.dto.CandidatoFiltro;
import br.com.sgps.candidato.domain.entity.Candidato;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;

import java.util.List;
import java.util.Optional;

public interface CandidatoRepositoryPort {


    boolean existeEmailCadastrado(Email email, CandidatoId id);

    boolean existeCpfCadastrado(String cpf, CandidatoId id);

    Pagina<Candidato> buscar(CandidatoFiltro candidatoFiltro, Paginacao paginacao);

    boolean existe(CandidatoId id);

    void persistir(Candidato candidato);

    Optional<Candidato> consultarPorId(CandidatoId id);

    List<Candidato> consultarTodos();

}
