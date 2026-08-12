package br.com.sgps.candidato.application.usecase;

import br.com.sgps.candidato.application.dto.CandidatoInput;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;
import br.com.sgps.candidato.application.port.in.CadastrarCandidato;
import br.com.sgps.candidato.domain.service.CandidatoService;
import org.springframework.stereotype.Service;

@Service
public class CadastrarCandidatoUseCase implements CadastrarCandidato{

    private final CandidatoService candidatoService;

    public CadastrarCandidatoUseCase(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @Override
    public CandidatoOutPut cadastrarCandidato(CandidatoInput candidatoInput) {

        return null;
    }
}
