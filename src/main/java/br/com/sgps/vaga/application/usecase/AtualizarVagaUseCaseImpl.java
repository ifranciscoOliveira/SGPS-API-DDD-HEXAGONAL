package br.com.sgps.vaga.application.usecase;

import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.vaga.application.dto.VagaInput;
import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.application.port.in.AtualizarVagaUseCase;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import br.com.sgps.vaga.domain.service.VagaServiceDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarVagaUseCaseImpl  implements AtualizarVagaUseCase {

    private final VagaServiceDomain vagaServiceDomain;
    private final VagaRepositoryPort vagaRepositoryPort;


    public AtualizarVagaUseCaseImpl(VagaServiceDomain  vagaServiceDomain, VagaRepositoryPort vagaRepositoryPort) {
        this.vagaServiceDomain = vagaServiceDomain;
        this.vagaRepositoryPort = vagaRepositoryPort;
    }

    @Override
    @Transactional
    public VagaOutPut alterar(UUID id, VagaInput vagaInput) {
        var vagaAlterar = vagaServiceDomain.alterar(
                new VagaId(id),
                vagaInput.titulo(),
                vagaInput.descricao(),
                vagaInput.dataInicio(),
                vagaInput.dataFim(),
                vagaInput.limiteInscricoes(),
                vagaInput.status(),
                vagaInput.observacao(),
                new InstituicaoId(vagaInput.instituicaoId()));
        var vagaAlterada = vagaRepositoryPort.persistir(vagaAlterar);
        return VagaOutPut.from(vagaAlterada);
    }
}
