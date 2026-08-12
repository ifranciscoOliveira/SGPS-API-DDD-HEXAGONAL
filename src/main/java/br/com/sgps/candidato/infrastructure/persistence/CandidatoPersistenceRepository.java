package br.com.sgps.candidato.infrastructure.persistence;

import br.com.sgps.candidato.infrastructure.entity.CandidatoPersistenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CandidatoPersistenceRepository  extends JpaRepository<CandidatoPersistenteEntity, UUID>, JpaSpecificationExecutor<CandidatoPersistenteEntity> {

    @Query("""
            SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END
            FROM CandidatoPersistenteEntity c 
            where c.email = :email and (:id IS NULL or c.id <> :id)
            """)
    boolean existEmailCadastrado(@Param("email") String email, @Param("id") UUID id);

    @Query("""
            SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END
            FROM CandidatoPersistenteEntity c 
            where c.cpf = :cpf and (:id IS NULL or c.id <> :id)
            """)
    boolean existCpfCadastrado(@Param("cpf") String cpf, @Param("id") UUID id);
}
