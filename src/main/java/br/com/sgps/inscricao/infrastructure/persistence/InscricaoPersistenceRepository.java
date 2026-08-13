package br.com.sgps.inscricao.infrastructure.persistence;

import br.com.sgps.inscricao.infrastructure.entity.InscricaoPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InscricaoPersistenceRepository extends JpaRepository<InscricaoPersistenceEntity, UUID> {


    boolean existsByCandidatoIdAndVagaId(UUID candidatoId, UUID vagaId);


}
