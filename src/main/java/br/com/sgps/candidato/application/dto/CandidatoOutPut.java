package br.com.sgps.candidato.application.dto;

import br.com.sgps.candidato.domain.entity.Candidato;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CandidatoOutPut {

    public String id;
    public String nome;
    public String email;
    public String telefone;

    public static CandidatoOutPut fromDomain(Candidato candidato) {
        return CandidatoOutPut.builder()
                .id(candidato.id().toString())
                .nome(candidato.nome())
                .email(candidato.email().value())
                .telefone(candidato.telefone())
                .build();
    }
}
