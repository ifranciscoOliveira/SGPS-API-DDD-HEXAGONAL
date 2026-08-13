package br.com.sgps.candidato.infrastructure.assembler;

import br.com.sgps.candidato.domain.entity.Candidato;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import br.com.sgps.candidato.infrastructure.entity.CandidatoPersistenteEntity;
import org.springframework.stereotype.Component;

@Component
public class CandidatoPersistenceEntityAssembler {


    public CandidatoPersistenteEntity fromDomain(Candidato candidato){
        return merge(new CandidatoPersistenteEntity(),candidato);
    }


    public CandidatoPersistenteEntity merge(CandidatoPersistenteEntity candidatoPersistenteEntity,
                                            Candidato candidato){
        candidatoPersistenteEntity.setId(candidato.id().value());
        candidatoPersistenteEntity.setCpf(candidato.cpf().value());
        candidatoPersistenteEntity.setNome(candidato.nome());
        candidatoPersistenteEntity.setEmail(candidato.email().value());
        candidatoPersistenteEntity.setTelefone(candidato.telefone());
        candidatoPersistenteEntity.setDataNascimento(candidato.dataNascimento());
        return candidatoPersistenteEntity;
    }

    public Candidato persistenceEntityToDoman(CandidatoPersistenteEntity candidatoPersistenteEntity){
        return Candidato.criarExistente()
                .id(new CandidatoId(candidatoPersistenteEntity.getId()))
                .nome(candidatoPersistenteEntity.getNome())
                .cpf(new Documento(candidatoPersistenteEntity.getCpf()))
                .email(new Email(candidatoPersistenteEntity.getEmail()))
                .telefone(candidatoPersistenteEntity.getTelefone())
                .dataNascimento(candidatoPersistenteEntity.getDataNascimento())
                .build();

    }


}
