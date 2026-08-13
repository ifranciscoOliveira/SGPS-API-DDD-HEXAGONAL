package br.com.sgps.vaga.application.usecase;

import br.com.sgps.vaga.application.dto.VagaInput;
import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.application.port.in.CadastrarVagaUseCase;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import br.com.sgps.vaga.domain.service.VagaServiceDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastrarVagaUseCaseImpl implements CadastrarVagaUseCase {

    private final VagaServiceDomain vagaServiceDomain;
    private final VagaRepositoryPort  vagaRepositoryPort;


    public CadastrarVagaUseCaseImpl(VagaServiceDomain vagaServiceDomain, VagaRepositoryPort vagaRepositoryPort) {
        this.vagaServiceDomain = vagaServiceDomain;
        this.vagaRepositoryPort = vagaRepositoryPort;
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
        vagaRepositoryPort.persistir(vaga);
        return VagaOutPut.from(vaga);
    }


}
