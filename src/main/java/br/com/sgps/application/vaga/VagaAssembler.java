package br.com.sgps.application.vaga;

import br.com.sgps.vaga.domain.entity.Vaga;
import org.springframework.stereotype.Component;

@Component
public class VagaAssembler {

    public VagaOutPutOld domainToOutPut(Vaga vaga){
        return VagaOutPutOld.builder()
                .id(vaga.id().value().toString())
                .titulo(vaga.titulo())
                .descricao(vaga.descricao())
                .dataInicio(vaga.dataInicio())
                .dataFim(vaga.dataFim())
                .limiteInscricoes(vaga.limiteInscricoes())
                .status(vaga.status())
                .observacao(vaga.observacao())
                .instituicaoId(vaga.instituicaoId().value())
                .build();
    }
}
