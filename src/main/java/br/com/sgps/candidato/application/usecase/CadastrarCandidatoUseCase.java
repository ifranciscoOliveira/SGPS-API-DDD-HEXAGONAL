package br.com.sgps.candidato.application.usecase;

import br.com.sgps.candidato.application.dto.CandidatoInput;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;
import br.com.sgps.candidato.application.port.in.CadastrarCandidato;
import br.com.sgps.candidato.application.port.out.CandidatoRepositoryPort;
import br.com.sgps.candidato.domain.entity.Candidato;
import br.com.sgps.candidato.domain.service.CandidatoServiceDomain;
import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import org.springframework.stereotype.Service;

@Service
public class CadastrarCandidatoUseCase implements CadastrarCandidato{

    private final CandidatoServiceDomain candidatoServiceDomain;
    private final CandidatoRepositoryPort candidatoRepositoryPort;

    public CadastrarCandidatoUseCase(CandidatoServiceDomain candidatoServiceDomain, CandidatoRepositoryPort candidatoRepositoryPort) {
        this.candidatoServiceDomain = candidatoServiceDomain;
        this.candidatoRepositoryPort = candidatoRepositoryPort;
    }

    @Override
    public CandidatoOutPut cadastrarCandidato(CandidatoInput candidatoInput) {
        Candidato candidato = candidatoServiceDomain.salvar(new Documento(candidatoInput.getCpf()),
                candidatoInput.getNome(),
                new Email(candidatoInput.getEmail()),
                candidatoInput.getTelefone(),
                candidatoInput.getDataNascimento());

        candidatoRepositoryPort.persistir(candidato);
        return CandidatoOutPut.fromDomain(candidato);
    }
}
