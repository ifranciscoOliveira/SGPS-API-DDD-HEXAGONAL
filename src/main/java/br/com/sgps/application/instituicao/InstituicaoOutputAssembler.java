package br.com.sgps.application.instituicao;

import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InstituicaoOutputAssembler {

    public InstituicaoOutPut toOutput(Instituicao instituicao) {
        return new InstituicaoOutPut(
                instituicao.id().value().toString(),
                instituicao.nome(),
                instituicao.cnpjCpf().value(),
                instituicao.telefone(),
                instituicao.email().value()
        );
    }

    public Instituicao toDomain (InstituicaoInput instituicaoInput, String id) {
        return Instituicao.criarExistente()
                .id(new InstituicaoId(UUID.fromString(id)))
                .nome(instituicaoInput.getNome())
                .cnpjCpf(new Documento(instituicaoInput.getCnpjCpf()))
                .telefone(instituicaoInput.getTelefone())
                .email(new Email(instituicaoInput.getEmail()))
                .build();
    }
}
