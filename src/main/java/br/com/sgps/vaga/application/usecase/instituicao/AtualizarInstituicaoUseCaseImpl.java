package br.com.sgps.vaga.application.usecase.instituicao;

import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.port.in.instituicao.AtualizarInstituicaoUseCase;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import br.com.sgps.vaga.domain.service.InstituicaoServiceDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AtualizarInstituicaoUseCaseImpl implements AtualizarInstituicaoUseCase {

    private final InstituicaoServiceDomain instituicaoServiceDomain;
    private final InstituicaoRepositoryPort instituicaoRepositoryPort;


    public AtualizarInstituicaoUseCaseImpl(InstituicaoServiceDomain instituicaoServiceDomain, InstituicaoRepositoryPort instituicaoRepositoryPort) {
        this.instituicaoServiceDomain = instituicaoServiceDomain;
        this.instituicaoRepositoryPort = instituicaoRepositoryPort;
    }

    @Override
    @Transactional
    public InstituicaoOutPut alterar(InstituicaoId id, InstituicaoInput instituicaoInput) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(instituicaoInput);
        var instituicaoAlterar = instituicaoServiceDomain.alterar(id,
                instituicaoInput.getNome(),
                new Documento(instituicaoInput.getCnpjCpf()),
                instituicaoInput.getTelefone(),
                new Email(instituicaoInput.getEmail()));

        instituicaoRepositoryPort.persistir(instituicaoAlterar);
        return new InstituicaoOutPut(instituicaoAlterar);
    }
}
