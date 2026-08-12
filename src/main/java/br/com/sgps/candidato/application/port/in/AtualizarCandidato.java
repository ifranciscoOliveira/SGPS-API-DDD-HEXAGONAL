package br.com.sgps.candidato.application.port.in;

import br.com.sgps.candidato.application.dto.CandidatoInput;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;

import java.util.UUID;

public interface AtualizarCandidato {

    CandidatoOutPut candidatoAtualizar(UUID id ,CandidatoInput candidatoInput);
}
