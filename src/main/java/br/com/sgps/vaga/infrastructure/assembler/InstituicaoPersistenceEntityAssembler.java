package br.com.sgps.vaga.infrastructure.assembler;

import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.infrastructure.entity.InstituicaoPersistenceEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstituicaoPersistenceEntityAssembler {


    public List<Instituicao> persistenceEntityToDomain(List<InstituicaoPersistenceEntity> listaEntity){

        List<Instituicao> listaInstituicoes = new ArrayList<Instituicao>();

        if (listaEntity != null) {
            listaInstituicoes.addAll(
                    listaEntity.stream()
                            .map(this::toDomain)
                            .toList()
            );
        }
        return listaInstituicoes;

    }

    public Instituicao toDomain(InstituicaoPersistenceEntity entity) {
        return Instituicao.criarExistente()
                .id(new InstituicaoId(entity.getId()))
                .nome(entity.getNome())
                .cnpjCpf(new Documento(entity.getCnpjCpf()))
                .telefone(entity.getTelefone())
                .email(new Email(entity.getEmail()))
                .build();
    }


}
