package br.com.sgps.candidato.application.port.in;

import br.com.sgps.candidato.application.dto.CandidatoInput;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;

public interface CadastrarCandidato {

    CandidatoOutPut cadastrarCandidato(CandidatoInput candidatoInput);

}
