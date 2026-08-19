package br.com.sgps.inscricao.domain.enums;

import lombok.Getter;

@Getter
public enum EtapasEnum {
    INSCRITO(1, "Inscrito"),
    ANALISE(2, "Análise"),
    TESTE(3, "Testes"),
    ENTREVISTA(4, "Entrevista"),
    AVALICACAO_FINAL(5, "Avaliação Final");


    private final int ordem;
    private final String descricao;

    EtapasEnum(int ordem, String descricao) {
        this.ordem = ordem;
        this.descricao = descricao;
    }

    public boolean isFinal() {
        return this == AVALICACAO_FINAL;
    }

    public static EtapasEnum getPorOrdem(int ordem) {
        for (EtapasEnum etapa : values()) {
            if (etapa.ordem == ordem) {
                return etapa;
            }
        }
        return null;
    }
}
