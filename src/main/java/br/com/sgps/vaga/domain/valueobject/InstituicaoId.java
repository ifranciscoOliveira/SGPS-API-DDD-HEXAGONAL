package br.com.sgps.vaga.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record InstituicaoId(UUID value) {

    public  InstituicaoId(){
        this(UUID.randomUUID());
    }

    public InstituicaoId(UUID value){
        Objects.requireNonNull(value);
        this.value  = value;
    }
}
