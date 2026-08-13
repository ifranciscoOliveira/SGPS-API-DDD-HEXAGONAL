package br.com.sgps.inscricao.infrastructure.assembler;

import br.com.sgps.inscricao.domain.entity.Inscricao;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.inscricao.domain.valueobject.InscricaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import br.com.sgps.candidato.infrastructure.entity.CandidatoPersistenteEntity;
import br.com.sgps.inscricao.infrastructure.entity.InscricaoPersistenceEntity;
import br.com.sgps.vaga.infrastructure.entity.VagaPersistenceEntity;
import org.springframework.stereotype.Component;

@Component
public class InscricaoPersistenceEntityAssembler {

    public InscricaoPersistenceEntity fromDomain(Inscricao inscricao) {
        return merge(InscricaoPersistenceEntity.builder().build(), inscricao);
    }

    public InscricaoPersistenceEntity merge(InscricaoPersistenceEntity inscricaoPersistenceEntity,
                                            Inscricao inscricao) {
        inscricaoPersistenceEntity.setId(inscricao.id().value());
        inscricaoPersistenceEntity.setCandidato(CandidatoPersistenteEntity.builder()
                .id(inscricao.candidatoId().value())
                .build());
        inscricaoPersistenceEntity.setVaga(VagaPersistenceEntity.builder()
                .id(inscricao.vagaId().value())
                .build());
        inscricaoPersistenceEntity.setDataInscricao(inscricao.dataInscricao());
        inscricaoPersistenceEntity.setResultadoInscricao(inscricao.resultadoInscricao());
        inscricaoPersistenceEntity.setEtapaAtual(inscricao.etapaAtual());

        return inscricaoPersistenceEntity;
    }

    public Inscricao fromPersistenceEntity(InscricaoPersistenceEntity inscricaoPersistenceEntity) {
        return Inscricao.criarExistente()
                .id(new InscricaoId(inscricaoPersistenceEntity.getId()))
                .candidatoId(new CandidatoId(
                        inscricaoPersistenceEntity.getCandidato().getId()))
                .vagaId(new VagaId(
                        inscricaoPersistenceEntity.getVaga().getId()))
                .dataInscricao(inscricaoPersistenceEntity.getDataInscricao())
                .resultadoInscricao(inscricaoPersistenceEntity.getResultadoInscricao())
                .etapaAtual(inscricaoPersistenceEntity.getEtapaAtual())
                .build();
    }
}
