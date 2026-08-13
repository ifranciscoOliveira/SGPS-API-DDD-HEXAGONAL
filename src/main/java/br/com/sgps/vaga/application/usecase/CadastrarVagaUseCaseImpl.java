package br.com.sgps.vaga.application.usecase;

import br.com.sgps.vaga.application.dto.VagaInput;
import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.application.port.in.CadastrarVagaUseCase;
import br.com.sgps.vaga.domain.service.VagaServiceDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastrarVagaUseCaseImpl implements CadastrarVagaUseCase {

    private final VagaServiceDomain vagaServiceDomain;


    public CadastrarVagaUseCaseImpl(VagaServiceDomain vagaServiceDomain) {
        this.vagaServiceDomain = vagaServiceDomain;
    }

    @Override
    @Transactional
    public VagaOutPut cadastrar(VagaInput vagaInput) {
        var vaga = vagaServiceDomain.salvar(vagaInput.titulo(),
                vagaInput.descricao(),
                vagaInput.dataInicio(),
                vagaInput.dataFim(),
                vagaInput.limiteInscricoes(),
                vagaInput.status(),
                vagaInput.observacao(),
                new InstituicaoId(vagaInput.instituicaoId())
        );
        return VagaOutPut.from(vaga);
    }


}
