package br.com.sgps.domain.repository;

import br.com.sgps.domain.commons.EtapasEnum;
import br.com.sgps.domain.commons.ResultadoInscricaoEnum;
import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.entity.Inscricao;
import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.infrastructure.assembler.CandidatoPersistenceEntityAssembler;
import br.com.sgps.infrastructure.assembler.InscricaoPersistenceEntityAssembler;
import br.com.sgps.vaga.infrastructure.assembler.InstituicaoPersistenceEntityAssembler;
import br.com.sgps.vaga.infrastructure.assembler.VagaPersistenceEntityAssembler;
import br.com.sgps.infrastructure.provider.CandidatoPersistenceProvider;
import br.com.sgps.infrastructure.provider.InscricaoPersistenceProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.UUID;

@DataJpaTest
@Import({
        InscricaoPersistenceProvider.class,
        InscricaoPersistenceEntityAssembler.class,
        CandidatoPersistenceProvider.class,
        CandidatoPersistenceEntityAssembler.class,
        VagaPersistenceEntityAssembler.class,
        InstituicaoPersistenceEntityAssembler.class
})
class InscricaoRepositoryDomainTestIT {

    private final InscricaoRepositoryDomain inscricaoRepositoryDomain;
    private final CandidatoRepositoryDomain candidatoRepositoryDomain;
    private final VagaRepositoryDomain vagaRepositoryDomain;
    private final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    @Autowired
    InscricaoRepositoryDomainTestIT(InscricaoRepositoryDomain inscricaoRepositoryDomain,
                                    CandidatoRepositoryDomain candidatoRepositoryDomain,
                                    VagaRepositoryDomain vagaRepositoryDomain,
                                    InstituicaoRepositoryDomain instituicaoRepositoryDomain) {
        this.inscricaoRepositoryDomain = inscricaoRepositoryDomain;
        this.candidatoRepositoryDomain = candidatoRepositoryDomain;
        this.vagaRepositoryDomain = vagaRepositoryDomain;
        this.instituicaoRepositoryDomain = instituicaoRepositoryDomain;
    }

    @Test
    void devePersistirEConsultarInscricaoPorIdComSucesso() {
        Candidato candidato = criarCandidato();
        Vaga vaga = criarVaga();
        Inscricao inscricao = Inscricao.criarNovaInscricao(candidato.id(), vaga.id());

        inscricaoRepositoryDomain.persistir(inscricao);

        var inscricaoConsultada = inscricaoRepositoryDomain.consultarPorId(inscricao.id().value());
        Assertions.assertThat(inscricaoConsultada).isPresent();
        Assertions.assertThat(inscricaoConsultada.get().id().value()).isEqualTo(inscricao.id().value());
        Assertions.assertThat(inscricaoConsultada.get().candidatoId()).isEqualTo(candidato.id());
        Assertions.assertThat(inscricaoConsultada.get().vagaId()).isEqualTo(vaga.id());
        Assertions.assertThat(inscricaoConsultada.get().etapaAtual()).isEqualTo(EtapasEnum.INSCRITO);
        Assertions.assertThat(inscricaoConsultada.get().resultadoInscricao()).isEqualTo(ResultadoInscricaoEnum.EM_AVALIACAO);
    }

    @Test
    void deveRetornarValoresValidosParaExistenciaPorCandidatoEVaga() {
        Candidato candidato = criarCandidato();
        Vaga vaga = criarVaga();
        Inscricao inscricao = Inscricao.criarNovaInscricao(candidato.id(), vaga.id());

        inscricaoRepositoryDomain.persistir(inscricao);

        Assertions.assertThat(inscricaoRepositoryDomain.existeInscricaoPorCandidatoEPorVaga(
                candidato.id().value(),
                vaga.id().value())).isTrue();

        Assertions.assertThat(inscricaoRepositoryDomain.existeInscricaoPorCandidatoEPorVaga(
                candidato.id().value(),
                UUID.randomUUID())).isFalse();
    }

    @Test
    void deveAlterarEtapaEResultadoComSucesso() {
        Candidato candidato = criarCandidato();
        Vaga vaga = criarVaga();
        Inscricao inscricao = Inscricao.criarNovaInscricao(candidato.id(), vaga.id());
        inscricaoRepositoryDomain.persistir(inscricao);

        inscricao.alterarEtapaAtual(EtapasEnum.AVALICACAO_FINAL);
        inscricao.aprovar();
        inscricaoRepositoryDomain.persistir(inscricao);

        var inscricaoAlterada = inscricaoRepositoryDomain.consultarPorId(inscricao.id().value());
        Assertions.assertThat(inscricaoAlterada).isPresent();
        Assertions.assertThat(inscricaoAlterada.get().etapaAtual()).isEqualTo(EtapasEnum.AVALICACAO_FINAL);
        Assertions.assertThat(inscricaoAlterada.get().resultadoInscricao()).isEqualTo(ResultadoInscricaoEnum.APROVADO);
    }

    private Candidato criarCandidato() {
        Candidato candidato = Candidato.criarNovoCandidato(
                new Documento("573.049.640-06"),
                "Candidato Teste",
                new Email("candidato.teste@teste.com"),
                "11999999999",
                LocalDate.of(1990, 1, 1)
        );
        candidatoRepositoryDomain.persistir(candidato);
        return candidato;
    }

    private Vaga criarVaga() {
        Instituicao instituicao = Instituicao.criarNovaInstituicao(
                "Instituicao Teste",
                new Documento("86.968.743/0001-75"),
                "1133334444",
                new Email("instituicao.teste@teste.com")
        );
        instituicaoRepositoryDomain.persistir(instituicao);

        Vaga vaga = Vaga.criarNovaVaga(
                "Vaga Teste",
                "Descricao da vaga teste",
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(30),
                10,
                "ATIVA",
                "Sem observacoes",
                instituicao.id()
        );

        return vagaRepositoryDomain.persistir(vaga);
    }
}
