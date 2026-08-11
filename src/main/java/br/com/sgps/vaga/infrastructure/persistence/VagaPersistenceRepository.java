package br.com.sgps.vaga.infrastructure.persistence;

import br.com.sgps.vaga.infrastructure.entity.VagaPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface VagaPersistenceRepository  extends JpaRepository<VagaPersistenceEntity, UUID>,
        JpaSpecificationExecutor<VagaPersistenceEntity> {

    @Query("""
            SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END
            FROM VagaPersistenceEntity v 
            where v.titulo = :titulo and (:id IS NULL or v.id <> :id)
            """)
    boolean existTituloCadastrado(@Param("titulo") String email, @Param("id") UUID id);

}
