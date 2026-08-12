package br.com.sgps.infrastructure.specification;

import br.com.sgps.candidato.application.dto.CandidatoFiltro;
import br.com.sgps.candidato.infrastructure.entity.CandidatoPersistenteEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

@Slf4j
public class CandidatoSpecification {

    public static Specification<CandidatoPersistenteEntity> filtrar(CandidatoFiltro candidatoFiltro) {

        if(candidatoFiltro == null){
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<>();

            if (candidatoFiltro.nome() != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + candidatoFiltro.nome().toLowerCase() + "%")
                );
            }

            if (candidatoFiltro.cpf() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("cpf"), candidatoFiltro.cpf())
                );
            }

            if (candidatoFiltro.email() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("email"), candidatoFiltro.email())
                );
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };

    }



}
