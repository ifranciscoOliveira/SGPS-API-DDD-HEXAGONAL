package br.com.sgps.vaga.application.usecase.instituicao;

import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.port.in.instituicao.CadastrarInstituicaoUseCase;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import br.com.sgps.vaga.domain.service.InstituicaoServiceDomain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CadastrarInstituicaoUseCaseImpl implements CadastrarInstituicaoUseCase {

    private final InstituicaoServiceDomain instituicaoServiceDomain;
    private final InstituicaoRepositoryPort instituicaoRepositoryPort;

    public CadastrarInstituicaoUseCaseImpl(InstituicaoServiceDomain instituicaoServiceDomain, InstituicaoRepositoryPort instituicaoRepositoryPort) {
        this.instituicaoServiceDomain = instituicaoServiceDomain;
        this.instituicaoRepositoryPort = instituicaoRepositoryPort;
    }

    @Override
    @Transactional
    public InstituicaoOutPut criar(InstituicaoInput instituicaoInput) {
        Objects.requireNonNull(instituicaoInput);

        var instituicao = instituicaoServiceDomain.salvar(instituicaoInput.getNome(),
                new Documento(instituicaoInput.getCnpjCpf()),
                instituicaoInput.getTelefone(),
                new Email(instituicaoInput.getEmail()));

        instituicaoRepositoryPort.persistir(instituicao);

        return new InstituicaoOutPut(instituicao);
    }
}
