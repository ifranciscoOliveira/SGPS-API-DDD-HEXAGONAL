package br.com.sgps.vaga.application.usecase.instituicao;

import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.port.in.instituicao.AtualizarInstituicaoUseCase;
import br.com.sgps.vaga.domain.service.InstituicaoServiceDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AtualizarInstituicaoUseCaseImpl implements AtualizarInstituicaoUseCase {

    private final InstituicaoServiceDomain instituicaoServiceDomain;

    public AtualizarInstituicaoUseCaseImpl(InstituicaoServiceDomain instituicaoServiceDomain) {
        this.instituicaoServiceDomain = instituicaoServiceDomain;
    }

    @Override
    public InstituicaoOutPut alterar(InstituicaoId id, InstituicaoInput instituicaoInput) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(instituicaoInput);
        var instituicaoAlterar = instituicaoServiceDomain.alterar(id,
                instituicaoInput.getNome(),
                new Documento(instituicaoInput.getCnpjCpf()),
                instituicaoInput.getTelefone(),
                new Email(instituicaoInput.getEmail()));


        return new InstituicaoOutPut(instituicaoAlterar);
    }
}
