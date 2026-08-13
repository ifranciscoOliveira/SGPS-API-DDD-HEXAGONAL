package br.com.sgps.inscricao.controller;


import br.com.sgps.inscricao.application.dto.InscricaoInput;
import br.com.sgps.inscricao.application.dto.InscricoesInput;
import br.com.sgps.inscricao.application.port.in.AvancarEtapaUseCase;
import br.com.sgps.inscricao.application.port.in.InscrisaoCandidatoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private final InscrisaoCandidatoUseCase inscrisaoCandidatoUseCase;
    private final AvancarEtapaUseCase avancarEtapaUseCase;



    @PostMapping("/inscrever")
    public void realizarInscricao(@RequestBody InscricaoInput inscricao) {
        inscrisaoCandidatoUseCase.inscreverCandidatoEmVaga(
                UUID.fromString(inscricao.getIdCandidato())
                ,UUID.fromString(inscricao.getIdVaga()));
    }

    @PostMapping("/avancar-etapa")
    public void avancarEtapaInscricao(@RequestBody InscricoesInput inscricoesInput) {
        inscricoesInput.getIdsInscricoes().forEach(idInscricao ->
            avancarEtapaUseCase.avancarEtapa(UUID.fromString(idInscricao.toString()))
        );
    }
}
