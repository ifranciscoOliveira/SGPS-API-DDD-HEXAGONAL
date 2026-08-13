package br.com.sgps.candidato.application.assembler;


import br.com.sgps.candidato.application.dto.CandidatoOutPut;
import br.com.sgps.candidato.domain.entity.Candidato;
import org.springframework.stereotype.Component;

@Component
public class CandidatoAssembler {

    public CandidatoOutPut  toCandidato(Candidato candidato) {
        return CandidatoOutPut.builder()
                .id(candidato.id().value().toString())
                .nome(candidato.nome())
                .email(candidato.email().value())
                .telefone(candidato.telefone())
                .build();
    }

}
