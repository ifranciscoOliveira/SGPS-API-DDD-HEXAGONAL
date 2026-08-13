package br.com.sgps.vaga.application.dto;

import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoInputAlterar {

    private String nome;
    private Documento cnpjCpf;
    private String telefone;
    private Email email;


}
