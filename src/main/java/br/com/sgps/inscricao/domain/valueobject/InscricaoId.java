package br.com.sgps.inscricao.domain.valueobject;

import java.util.UUID;

public record InscricaoId(UUID value) {

    public InscricaoId() {
        this(UUID.randomUUID());
    }

    public InscricaoId(UUID value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
