package br.com.sgps.vaga.application.usecase;

import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.vaga.application.dto.VagaInput;
import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.application.port.in.CadastrarVagaUseCase;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import br.com.sgps.vaga.domain.service.VagaServiceDomain;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CadastrarVagaUseCaseImpl implements CadastrarVagaUseCase {

    private final VagaServiceDomain vagaServiceDomain;
    private final InstituicaoRepositoryPort instituicaoRepositoryPort;


    public CadastrarVagaUseCaseImpl(VagaServiceDomain vagaServiceDomain, InstituicaoRepositoryPort instituicaoRepositoryPort) {
        this.vagaServiceDomain = vagaServiceDomain;
        this.instituicaoRepositoryPort = instituicaoRepositoryPort;
    }

    @Override
    @Transactional
    public VagaOutPut cadastrar(VagaInput vagaInput) {
        existeInstituicao(new InstituicaoId(vagaInput.instituicaoId()));
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

    private void existeInstituicao(InstituicaoId instituicaoId){
        Objects.requireNonNull(instituicaoId);
        if(!instituicaoRepositoryPort.existe(instituicaoId)){
            throw new NegocioException("Instituição não encontrada com o ID: " + instituicaoId);
        }
    }
}
