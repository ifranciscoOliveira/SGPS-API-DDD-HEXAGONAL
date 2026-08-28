package br.com.sgps.candidato.application.usecase;

import br.com.sgps.candidato.application.dto.CandidatoInput;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;
import br.com.sgps.candidato.application.port.in.AtualizarCandidato;
import br.com.sgps.candidato.application.port.out.CandidatoRepositoryPort;
import br.com.sgps.candidato.domain.entity.Candidato;
import br.com.sgps.candidato.domain.service.CandidatoServiceDomain;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.shared.domain.valueobject.Email;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarCandidatoUseCase implements AtualizarCandidato {

    private final CandidatoServiceDomain candidatoServiceDomain;
    private final CandidatoRepositoryPort candidatoRepositoryPort;

    public AtualizarCandidatoUseCase(CandidatoServiceDomain candidatoServiceDomain, CandidatoRepositoryPort candidatoRepositoryPort) {
        this.candidatoServiceDomain = candidatoServiceDomain;
        this.candidatoRepositoryPort = candidatoRepositoryPort;
    }


    @Override
    public CandidatoOutPut candidatoAtualizar(UUID id, CandidatoInput candidatoInput) {

        Candidato candidato =  candidatoServiceDomain.alterar(new CandidatoId(id),
                candidatoInput.getNome(),
                new Email(candidatoInput.getEmail()),
                candidatoInput.getTelefone(),
                candidatoInput.getDataNascimento());

        candidatoRepositoryPort.persistir(candidato);
        return CandidatoOutPut.fromDomain(candidato);
    }
}
