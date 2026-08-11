package br.com.sgps.domain.repository;

import br.com.sgps.application.candidato.CandidatoFiltro;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;

import java.util.List;
import java.util.Optional;

public interface CandidatoRepositoryDomain {

    boolean existeEmailCadastrado(Email email, CandidatoId id);

    boolean existeCpfCadastrado(String cpf, CandidatoId id);

    Pagina<Candidato> buscar(CandidatoFiltro candidatoFiltro, Paginacao paginacao);

    boolean existe(CandidatoId id);

    void persistir(Candidato candidato);

    Optional<Candidato> consultarPorId(CandidatoId id);

    List<Candidato> consultarTodos();
}
