package br.com.sgps.vaga.infrastructure.assembler;

import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import br.com.sgps.infrastructure.entity.InstituicaoPersistenceEntity;
import br.com.sgps.infrastructure.entity.VagaPersistenceEntity;
import org.springframework.stereotype.Component;

@Component
public class VagaPersistenceEntityAssembler {


    public VagaPersistenceEntity fromDomain(Vaga vaga){
        return merge(VagaPersistenceEntity.builder().build(), vaga);
    }

    public VagaPersistenceEntity merge(VagaPersistenceEntity vagaPersistenceEntity
    , Vaga vaga){

        vagaPersistenceEntity.setId(vaga.id().value());
        vagaPersistenceEntity.setTitulo(vaga.titulo());
        vagaPersistenceEntity.setDescricao(vaga.descricao());
        vagaPersistenceEntity.setDataInicio(vaga.dataInicio());
        vagaPersistenceEntity.setDataFim(vaga.dataFim());
        vagaPersistenceEntity.setLimiteInscricoes(vaga.limiteInscricoes());
        vagaPersistenceEntity.setStatus(vaga.status());
        vagaPersistenceEntity.setObservacao(vaga.observacao());
        vagaPersistenceEntity.setInstituicao(new InstituicaoPersistenceEntity());
        vagaPersistenceEntity.getInstituicao().setId(vaga.instituicaoId().value());
        return vagaPersistenceEntity;
    }

    public Vaga persistenceEntityToDomain(VagaPersistenceEntity vagaPersistenceEntity){
        return Vaga.criarExistente()
                .id(new VagaId(vagaPersistenceEntity.getId()))
                .titulo(vagaPersistenceEntity.getTitulo())
                .descricao(vagaPersistenceEntity.getDescricao())
                .dataInicio(vagaPersistenceEntity.getDataInicio())
                .dataFim(vagaPersistenceEntity.getDataFim())
                .limiteInscricoes(vagaPersistenceEntity.getLimiteInscricoes())
                .status(vagaPersistenceEntity.getStatus())
                .observacao(vagaPersistenceEntity.getObservacao())
                .instituicaoId(new InstituicaoId(
                        vagaPersistenceEntity.getInstituicao().getId()))
                .build();
    }
}
