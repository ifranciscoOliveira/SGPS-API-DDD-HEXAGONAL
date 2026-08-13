package br.com.sgps.inscricao.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoInput {

    private String idCandidato;
    private String idVaga;
    
}
