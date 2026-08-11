package br.com.sgps.infrastructure.entity;

import br.com.sgps.domain.commons.EtapasEnum;
import br.com.sgps.domain.commons.ResultadoInscricaoEnum;
import br.com.sgps.vaga.infrastructure.entity.VagaPersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inscricao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoPersistenceEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_candidato", nullable = false)
    private CandidatoPersistenteEntity candidato;

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private VagaPersistenceEntity vaga;


    @Column(name = "data_inscricao")
    private LocalDateTime dataInscricao;

    @Enumerated(EnumType.STRING)
    @Column(name ="etapa_atual")
    private EtapasEnum etapaAtual;

    @Column(name ="resultado")
    @Enumerated(EnumType.STRING)
    private ResultadoInscricaoEnum resultadoInscricao;
}
