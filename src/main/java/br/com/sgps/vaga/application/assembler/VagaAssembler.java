package br.com.sgps.vaga.application.assembler;

import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.domain.entity.Vaga;
import org.springframework.stereotype.Component;

@Component
public class VagaAssembler {

    public VagaOutPut domainToOutPut(Vaga vaga){
        return VagaOutPut.builder()
                .id(vaga.id().value())
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
