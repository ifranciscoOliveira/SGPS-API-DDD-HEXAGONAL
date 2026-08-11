package br.com.sgps.vaga.application.dto;

import br.com.sgps.vaga.domain.entity.Instituicao;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoOutPut {

    private String id;
    private String nome;
    private String cnpjCpf;
    private String telefone;
    private String email;

    public InstituicaoOutPut(Instituicao instituicao){
        this.id = instituicao.id().value().toString();
        this.nome = instituicao.nome();
        this.cnpjCpf =instituicao.cnpjCpf().value();
        this.email = instituicao.email().value();
        this.telefone = instituicao.telefone();
    }
}
