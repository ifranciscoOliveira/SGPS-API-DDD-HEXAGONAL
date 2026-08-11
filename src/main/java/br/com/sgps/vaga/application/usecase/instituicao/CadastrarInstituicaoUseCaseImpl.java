package br.com.sgps.vaga.application.usecase.instituicao;

import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.port.in.instituicao.CadastrarInstituicaoUseCase;
import br.com.sgps.vaga.domain.service.InstituicaoServiceDomain;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CadastrarInstituicaoUseCaseImpl implements CadastrarInstituicaoUseCase {

    private final InstituicaoServiceDomain instituicaoServiceDomain;

    public CadastrarInstituicaoUseCaseImpl(InstituicaoServiceDomain instituicaoServiceDomain) {
        this.instituicaoServiceDomain = instituicaoServiceDomain;
    }

    @Override
    public InstituicaoOutPut criar(InstituicaoInput instituicaoInput) {
        Objects.requireNonNull(instituicaoInput);

        var instituicao = instituicaoServiceDomain.salvar(instituicaoInput.getNome(),
                new Documento(instituicaoInput.getCnpjCpf()),
                instituicaoInput.getTelefone(),
                new Email(instituicaoInput.getEmail()));

        return new InstituicaoOutPut(instituicao);
    }
}
