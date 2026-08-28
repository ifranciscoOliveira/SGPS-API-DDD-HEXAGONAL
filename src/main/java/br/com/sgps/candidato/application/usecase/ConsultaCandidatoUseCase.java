package br.com.sgps.candidato.application.usecase;

import br.com.sgps.candidato.application.dto.CandidatoFiltro;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;
import br.com.sgps.candidato.application.port.in.ConsultaCandidato;
import br.com.sgps.candidato.application.port.out.CandidatoRepositoryPort;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaCandidatoUseCase implements ConsultaCandidato {

    private final CandidatoRepositoryPort candidatoRepositoryPort;

    public ConsultaCandidatoUseCase(CandidatoRepositoryPort candidatoRepositoryPort) {
        this.candidatoRepositoryPort = candidatoRepositoryPort;
    }

    @Override
    public CandidatoOutPut consultarPorId(CandidatoId id) {
        return candidatoRepositoryPort.consultarPorId(id).map(CandidatoOutPut::fromDomain).orElse(null);
    }

    @Override
    public List<CandidatoOutPut> consultarTodos() {
        return candidatoRepositoryPort.consultarTodos().stream().map(CandidatoOutPut::fromDomain).toList();
    }

    @Override
    public Pagina<CandidatoOutPut> buscar(CandidatoFiltro candidatoFiltro, Paginacao paginacao) {
        return candidatoRepositoryPort.buscar(candidatoFiltro, paginacao).map(CandidatoOutPut::fromDomain);
    }
}
