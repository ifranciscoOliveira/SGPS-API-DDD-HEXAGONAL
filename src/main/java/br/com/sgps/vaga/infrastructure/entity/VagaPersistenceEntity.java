package br.com.sgps.vaga.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "vaga")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VagaPersistenceEntity {

    @Id
    private UUID id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_inicio", nullable = false )
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false )
    private LocalDate dataFim;

    @Column(name = "limite_instcricao", nullable = false )
    private Integer limiteInscricoes;

    @Column(name = "status", nullable = false )
    private String status;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_instituicao",nullable = false)
    private InstituicaoPersistenceEntity instituicao;
}
