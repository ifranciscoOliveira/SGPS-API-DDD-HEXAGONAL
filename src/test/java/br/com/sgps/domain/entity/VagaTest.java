package br.com.sgps.domain.entity;

import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.domain.entity.Vaga;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

class VagaTest {



    @Test
    void deveCriarNovaVagaComSucesso() {

        Vaga vaga = Vaga.criarNovaVaga("vaga dev full",
                "vaga para dev java full",
                LocalDate.now(),
                LocalDate.now().plusWeeks(1),
                5,
                "",
                "observacapo",
                new InstituicaoId(UUID.randomUUID()));

        Assertions.assertNotNull(vaga.id());
        Assertions.assertNotNull(vaga.instituicaoId());


    }

    @Test
    void validarPeriodoDeInscricaoParaVagaPeriodoFuturo() {

        Vaga vaga = Vaga.criarNovaVaga("vaga dev full",
                "vaga para dev java full",
                LocalDate.now().plusWeeks(5),
                LocalDate.now().plusWeeks(10),
                5,
                "",
                "observacapo",
                new InstituicaoId(UUID.randomUUID()));

        Assertions.assertThrows(NegocioException.class, vaga::validarPeriodoDeInscricaoParaVaga);

    }

    @Test
    void validarPeriodoDeInscricaoParaVagaPeriodoPessado() {

        Vaga vaga = Vaga.criarNovaVaga("vaga dev full",
                "vaga para dev java full",
                LocalDate.now().minusWeeks(10),
                LocalDate.now().minusWeeks(5),
                5,
                "",
                "observacapo",
                new InstituicaoId(UUID.randomUUID()));

        Assertions.assertThrows(NegocioException.class, vaga::validarPeriodoDeInscricaoParaVaga);

    }

    @Test
    void deveValidarAlterarDataInicioPeriodoDeInscricao() {

        Vaga vaga = Vaga.criarNovaVaga("vaga dev full",
                "vaga para dev java full",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                5,
                "",
                "observacapo",
                new InstituicaoId(UUID.randomUUID()));

        Assertions.assertThrows(NegocioException.class,()-> vaga.alterarDataInicio(vaga.dataInicio().plusDays(4)));

    }


    @Test
    void criarExistente() {
    }
}