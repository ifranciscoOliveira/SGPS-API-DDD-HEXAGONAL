package br.com.sgps.candidato.application.port.in;

import br.com.sgps.candidato.application.dto.CandidatoFiltro;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;

import java.util.List;

public interface ConsultaCandidato {

    CandidatoOutPut consultarPorId(CandidatoId id);

    List<CandidatoOutPut> consultarTodos();

    Pagina<CandidatoOutPut> buscar(CandidatoFiltro candidatoFiltro, Paginacao paginacao);
}
