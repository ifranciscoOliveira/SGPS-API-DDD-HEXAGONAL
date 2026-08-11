package br.com.sgps.vaga.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoInput {

    private String nome;
    private String cnpjCpf;
    private String telefone;
    private String email;
}
