package br.com.sgps.inscricao.application.usecase;

import br.com.sgps.inscricao.domain.valueobject.InscricaoId;
import br.com.sgps.inscricao.application.port.in.AvancarEtapaUseCase;
import br.com.sgps.inscricao.application.port.out.InscrticaoRepositoryPort;
import br.com.sgps.inscricao.domain.entity.Inscricao;
import br.com.sgps.inscricao.domain.service.InscricaoServiceDomain;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AvancarEtapaUseCaseImpl implements AvancarEtapaUseCase {

    private final InscricaoServiceDomain inscricaoServiceDomain;
    private final InscrticaoRepositoryPort inscricaoRepositoryPort;

    public AvancarEtapaUseCaseImpl(InscricaoServiceDomain inscricaoServiceDomain, InscrticaoRepositoryPort inscricaoRepositoryPort){
        this.inscricaoServiceDomain = inscricaoServiceDomain;
        this.inscricaoRepositoryPort = inscricaoRepositoryPort;
    }

    @Override
    public void avancarEtapa(UUID idInscricao) {
        Inscricao inscricao = inscricaoServiceDomain.avancarProximaEtapaDaInscricao(new InscricaoId(idInscricao));
        inscricaoRepositoryPort.persistir(inscricao);
    }
}
