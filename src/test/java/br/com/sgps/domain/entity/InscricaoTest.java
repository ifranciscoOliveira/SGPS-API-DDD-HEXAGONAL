package br.com.sgps.domain.entity;

import br.com.sgps.shared.domain.enums.EtapasEnum;
import br.com.sgps.shared.domain.enums.ResultadoInscricaoEnum;
import br.com.sgps.shared.domain.exception.NegocioException;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.inscricao.domain.entity.Inscricao;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.UUID;

class InscricaoTest {

    private Inscricao criarInscricao(){
       return Inscricao.criarNovaInscricao(new CandidatoId(UUID.randomUUID()),
                new VagaId(UUID.randomUUID()));
    }

    @Test
    void deveCriarNovaInscricaoComSucesso() {

        CandidatoId candidatoId = new CandidatoId();
        VagaId vagaId = new VagaId();

        Inscricao inscricao = Inscricao.criarNovaInscricao(candidatoId,vagaId);

        Assertions.assertNotNull(inscricao);
        Assertions.assertNotNull(inscricao.id());
        Assertions.assertEquals(candidatoId,inscricao.candidatoId());
        Assertions.assertEquals(vagaId,inscricao.vagaId());
        Assertions.assertEquals(EtapasEnum.INSCRITO,inscricao.etapaAtual());
        Assertions.assertEquals( ResultadoInscricaoEnum.EM_AVALIACAO, inscricao.resultadoInscricao());

    }

    @Test
    void deveAprovarComSucesso() {
        Inscricao inscricao = criarInscricao();
        inscricao.alterarEtapaAtual(EtapasEnum.AVALICACAO_FINAL);
        inscricao.aprovar();
    }


    @ParameterizedTest
    @EnumSource(
            value = EtapasEnum.class,
            names = "AVALICACAO_FINAL",
            mode = EnumSource.Mode.EXCLUDE)
    void deveAprovarSemSucesso(EtapasEnum etapas){
        Inscricao inscricao = criarInscricao();
        inscricao.alterarEtapaAtual(etapas);
        Assertions.assertThrows(NegocioException.class, ()-> inscricao.aprovar());

    }

    @Test
    void reprovarComSucesso() {

        Inscricao inscricao = criarInscricao();
        inscricao.reprovar();

    }

    @ParameterizedTest
    @EnumSource(value = EtapasEnum.class)
    void alterarEtapaAtualComSucesso(EtapasEnum etapas) {
        Inscricao inscricao = criarInscricao();
        inscricao.alterarEtapaAtual(etapas);
    }

    @Test
    void alterarEtapaAtualSemSucesso() {
        Inscricao inscricao = criarInscricao();
        Assertions.assertThrows(NullPointerException.class,
                ()-> inscricao.alterarEtapaAtual(null));
    }

    @ParameterizedTest
    @EnumSource(value = EtapasEnum.class,
    mode = EnumSource.Mode.EXCLUDE,
    names = "AVALICACAO_FINAL")
    void alterarParaProximaEtapaComSucesso(EtapasEnum etapa) {

        Inscricao inscricao = criarInscricao();
        inscricao.alterarEtapaAtual(etapa);
        inscricao.alterarParaProximaEtapa();

        Assertions.assertEquals(proximaEtapaEsperada(etapa), inscricao.etapaAtual());

    }

    @Test
    void alterarParaProximaEtapaSemSucesso() {

        Inscricao inscricao = criarInscricao();
        inscricao.alterarEtapaAtual(EtapasEnum.AVALICACAO_FINAL);

        Assertions.assertThrows(NegocioException.class, ()-> inscricao.alterarParaProximaEtapa());
    }

    @Test
    void alterarParaProximaEtapaAprovado() {

        Inscricao inscricao = criarInscricao();
        inscricao.alterarEtapaAtual(EtapasEnum.AVALICACAO_FINAL);
        inscricao.aprovar();
        Assertions.assertThrows(NegocioException.class, ()-> inscricao.alterarParaProximaEtapa());
    }
    @Test
    void alterarParaProximaEtapaReprovado() {

        Inscricao inscricao = criarInscricao();
        inscricao.reprovar();
        Assertions.assertThrows(NegocioException.class, ()-> inscricao.alterarParaProximaEtapa());
    }


    private EtapasEnum proximaEtapaEsperada(EtapasEnum atual) {
        switch (atual) {
            case INSCRITO: return EtapasEnum.ANALISE;
            case ANALISE: return EtapasEnum.TESTE;
            case TESTE: return EtapasEnum.ENTREVISTA;
            case ENTREVISTA: return EtapasEnum.AVALICACAO_FINAL;
            default: throw new IllegalArgumentException();
        }
    }
}