package br.com.sgps.vaga.infrastructure.persistence;

import br.com.sgps.infrastructure.entity.InstituicaoPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface InstituicaoPersistenceRporitoy  extends JpaRepository<InstituicaoPersistenceEntity, UUID>,
        JpaSpecificationExecutor<InstituicaoPersistenceEntity> {

    @Query("""
            SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END
            FROM InstituicaoPersistenceEntity i 
            where i.email = :email and (:id IS NULL or i.id <> :id)
            """)
    boolean existEmailCadastrado(@Param("email") String email, @Param("id") UUID id);

    @Query("""
            SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END
            FROM InstituicaoPersistenceEntity i 
            where i.cnpjCpf = :cnpjCpf and (:id IS NULL or i.id <> :id)
            """)
    boolean existCpfCadastrado(@Param("cnpjCpf") String cnpjCpf, @Param("id") UUID id);
}
