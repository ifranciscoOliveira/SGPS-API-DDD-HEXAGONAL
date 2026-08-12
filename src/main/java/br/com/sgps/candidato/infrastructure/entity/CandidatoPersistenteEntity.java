package br.com.sgps.candidato.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "candidato")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatoPersistenteEntity {

    @Id
    private UUID id;
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;
    @Column(nullable = false, length = 255)
    private String nome;
    @Column(nullable = false, length = 255, unique = true)
    private String email;
    @Column(nullable = false, length = 20)
    private String telefone;
    @Column(nullable = false)
    private LocalDate dataNascimento;
}
