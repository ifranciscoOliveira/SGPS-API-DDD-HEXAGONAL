package br.com.sgps.vaga.infrastructure.specification;

import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.vaga.infrastructure.entity.VagaPersistenceEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

@Slf4j
public class VagaSpecification {

    public static Specification<VagaPersistenceEntity> filtrar(VagaFiltro vagaFiltro) {

        if(vagaFiltro == null){
             return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (vagaFiltro.titulo() != null && !vagaFiltro.titulo().isBlank()) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + vagaFiltro.titulo().toLowerCase() + "%")
                );
            }

            if (vagaFiltro.status() != null && !vagaFiltro.status().isBlank()) {
                predicates.add(
                        criteriaBuilder.equal(root.get("status"), vagaFiltro.status())
                );
            }

            if (vagaFiltro.instituicaoId() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("instituicao").get("id"), vagaFiltro.instituicaoId())
                );
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };

    }

}
