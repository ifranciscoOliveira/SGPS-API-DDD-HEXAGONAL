package br.com.sgps.domain.service;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.entity.Inscricao;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@DomainService
@RequiredArgsConstructor
public class InscricaoServiceDomain {


    public Inscricao realizarInscricao(Vaga vaga, Candidato candidato) {
        Objects.requireNonNull(vaga);
        Objects.requireNonNull(candidato);
        Inscricao inscricao = Inscricao.criarNovaInscricao(new CandidatoId(candidato.id().value())
                , new VagaId(vaga.id().value()));

        vaga.validarPeriodoDeInscricaoParaVaga();


        return inscricao;
    }

}
