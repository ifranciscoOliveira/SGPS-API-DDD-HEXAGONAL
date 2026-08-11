package br.com.sgps.vaga.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "instituicao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstituicaoPersistenceEntity {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cnpjCpf;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false, unique = true)
    private String email;
}
