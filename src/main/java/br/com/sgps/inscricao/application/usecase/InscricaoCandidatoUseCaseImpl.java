package br.com.sgps.inscricao.application.usecase;

import br.com.sgps.inscricao.application.port.in.InscrisaoCandidatoUseCase;
import br.com.sgps.inscricao.application.port.out.InscrticaoRepositoryPort;
import br.com.sgps.inscricao.domain.entity.Inscricao;
import br.com.sgps.inscricao.domain.service.InscricaoServiceDomain;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InscricaoCandidatoUseCaseImpl implements InscrisaoCandidatoUseCase {

    private final InscricaoServiceDomain  inscricaoServiceDomain;
    private final InscrticaoRepositoryPort inscricaoRepositoryPort;


    public InscricaoCandidatoUseCaseImpl( InscricaoServiceDomain inscricaoServiceDomain, InscrticaoRepositoryPort inscricaoRepositoryPort) {
        this.inscricaoServiceDomain = inscricaoServiceDomain;
        this.inscricaoRepositoryPort = inscricaoRepositoryPort;
    }


    @Override
    public void inscreverCandidatoEmVaga(UUID candidatoId, UUID vagaId) {
        Inscricao inscricao = inscricaoServiceDomain.realizarInscricao(candidatoId, vagaId);
        inscricaoRepositoryPort.persistir(inscricao);
    }
}
