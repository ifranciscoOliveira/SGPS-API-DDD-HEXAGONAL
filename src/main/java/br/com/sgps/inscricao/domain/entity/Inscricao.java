package br.com.sgps.inscricao.domain.entity;

import br.com.sgps.shared.domain.enums.EtapasEnum;
import br.com.sgps.shared.domain.enums.ResultadoInscricaoEnum;
import br.com.sgps.shared.domain.exception.NegocioException;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import br.com.sgps.inscricao.domain.valueobject.InscricaoId;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Inscricao {

    private InscricaoId id;

    private CandidatoId candidatoId;

    private VagaId vagaId;

    private LocalDateTime dataInscricao;

    private EtapasEnum etapaAtual;

    private ResultadoInscricaoEnum resultadoInscricao;

    private void setId(InscricaoId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setCandidatoId(CandidatoId candidatoId) {
        Objects.requireNonNull(candidatoId);
        this.candidatoId = candidatoId;
    }

    private void setVagaId(VagaId vagaId) {
        Objects.requireNonNull(vagaId);
        this.vagaId = vagaId;
    }

    private void setDataInscricao(LocalDateTime dataInscricao) {
        Objects.requireNonNull(dataInscricao);
        this.dataInscricao = dataInscricao;
    }
    private void setResultadoInscricao(ResultadoInscricaoEnum resultadoInscricao) {
        if(ResultadoInscricaoEnum.APROVADO.equals(resultadoInscricao)){
            validarEtapaFinal();
        }
        this.resultadoInscricao = resultadoInscricao;
    }

    private void validarEtapaFinal() {
        if (!etapaAtual.isFinal()) {
            throw new NegocioException(
                    "A inscrição só pode ser finalizada na etapa final"
            );
        }
    }
    @Builder(builderClassName = "InscricaoExistenteBuild",builderMethodName = "criarExistente")
    public Inscricao(InscricaoId id,
                     CandidatoId candidatoId,
                     VagaId vagaId,
                     LocalDateTime dataInscricao,
                     EtapasEnum etapaAtual,
                     ResultadoInscricaoEnum resultadoInscricao) {
        setId(id);
        setCandidatoId(candidatoId);
        setVagaId(vagaId);
        setDataInscricao(dataInscricao);
        alterarEtapaAtual(etapaAtual);
        setResultadoInscricao(resultadoInscricao);
    }

    public static Inscricao criarNovaInscricao(CandidatoId candidatoId,
                                             VagaId vagaId) {
        return new Inscricao(
                new InscricaoId(),
                candidatoId,
                vagaId,
                LocalDateTime.now(),
                EtapasEnum.INSCRITO,
                ResultadoInscricaoEnum.EM_AVALIACAO
        );
    }

    public static Inscricao reconstruirInscricao(InscricaoId id,
                                             CandidatoId candidatoId,
                                             VagaId vagaId,
                                             LocalDateTime dataInscricao,
                                             EtapasEnum etapaAtual,
                                             ResultadoInscricaoEnum aprovado) {
        return new Inscricao(
                id,
                candidatoId,
                vagaId,
                dataInscricao,
                etapaAtual,
                aprovado
        );
    }




    public InscricaoId id() {
        return id;
    }

    public CandidatoId candidatoId() {
        return candidatoId;
    }

    public VagaId vagaId() {
        return vagaId;
    }

    public LocalDateTime dataInscricao() {
        return dataInscricao;
    }

    public EtapasEnum etapaAtual() {
        return etapaAtual;
    }

    public ResultadoInscricaoEnum resultadoInscricao() {
        return resultadoInscricao;
    }

    public Boolean aprovado() {
        return ResultadoInscricaoEnum.APROVADO.equals(resultadoInscricao);
    }
    public Boolean reprovado() {
        return ResultadoInscricaoEnum.REPROVADO.equals(resultadoInscricao);
    }

    public void aprovar() {
        validarEtapaFinal();
        setResultadoInscricao(ResultadoInscricaoEnum.APROVADO);
    }


    public void reprovar() {
        setResultadoInscricao(ResultadoInscricaoEnum.REPROVADO);
    }

    public void alterarEtapaAtual(EtapasEnum novaEtapa) {
        Objects.requireNonNull(novaEtapa);
        this.etapaAtual = novaEtapa;
    }

    public void alterarParaProximaEtapa(){
        if(aprovado()){
            throw new NegocioException("Inscrição aprovada não pode avançar para próxima etapa");
        }
        if(reprovado()){
            throw new NegocioException("Inscrição reprovada não pode avançar para próxima etapa");
        }
        EtapasEnum novaEtapa = Arrays.stream(EtapasEnum.values())
                .filter(e -> e.getOrdem() == etapaAtual().getOrdem() + 1)
                .findFirst()
                .orElseThrow(() -> new NegocioException("Nao existe proxima etapa para a etapa atual: " + etapaAtual()));

        alterarEtapaAtual(novaEtapa);

    }


}
