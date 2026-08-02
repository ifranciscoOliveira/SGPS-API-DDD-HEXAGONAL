package br.com.sgps.vaga.infrastructure.specification;

import br.com.sgps.application.instituicao.InstituicaoFiltro;
import br.com.sgps.infrastructure.entity.InstituicaoPersistenceEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class InstituicaoSpecification {

    public static Specification<InstituicaoPersistenceEntity> filtrar(InstituicaoFiltro instituicaoFiltro) {

        if(instituicaoFiltro ==  null){
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates =
                    new ArrayList<>();

            if (instituicaoFiltro.nome() != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + instituicaoFiltro.nome().toLowerCase() + "%")
                );
            }

            if (instituicaoFiltro.cnpjCpf() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("cnpjCpf"), instituicaoFiltro.cnpjCpf())
                );
            }

            if (instituicaoFiltro.email() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("email"), instituicaoFiltro.email())
                );
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
