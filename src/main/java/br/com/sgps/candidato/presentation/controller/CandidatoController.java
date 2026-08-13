package br.com.sgps.candidato.presentation.controller;

import br.com.sgps.candidato.application.dto.CandidatoInput;
import br.com.sgps.candidato.application.dto.CandidatoFiltro;
import br.com.sgps.candidato.application.dto.CandidatoOutPut;
import br.com.sgps.candidato.application.port.in.AtualizarCandidato;
import br.com.sgps.candidato.application.port.in.CadastrarCandidato;
import br.com.sgps.candidato.application.port.in.ConsultaCandidato;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.candidato.domain.valueobject.CandidatoId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidato")
@RequiredArgsConstructor
public class CandidatoController {

    private final CadastrarCandidato candidatoCadastro;
    private final AtualizarCandidato atualizarCandidato;;
    private final ConsultaCandidato consultarCandidato;


    @GetMapping
    public List<CandidatoOutPut> consultarTodos() {
       return  consultarCandidato.consultarTodos()
                .stream()
                .toList();
    }

    @GetMapping("/buscar")
    public Pagina<CandidatoOutPut> buscar(CandidatoFiltro candidatoFiltro, Pageable pageable) {

        Paginacao paginacao = new Paginacao(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort().toString(),
                null
        );

        return  consultarCandidato.buscar(candidatoFiltro, paginacao);
    }


    @GetMapping("/{idCandidato}")
    public CandidatoOutPut consultarCandidatoPorId(String idCandidato) {

        CandidatoOutPut candidato = consultarCandidato.consultarPorId(new CandidatoId(UUID.fromString(idCandidato)));

        return candidato;


    }

    @PostMapping
    public CandidatoOutPut cadastrarCandidato(@RequestBody CandidatoInput input) {
        CandidatoOutPut candidatoSalvo = candidatoCadastro.cadastrarCandidato(input);
        return candidatoSalvo;

    }

    @PutMapping("/{idCandidato}")
    public CandidatoOutPut alterarCandidato(@RequestBody CandidatoInput input, @PathVariable String idCandidato) {
        CandidatoOutPut candidatoAlterado = atualizarCandidato.candidatoAtualizar(UUID.fromString(idCandidato), input);
        return candidatoAlterado;
    }



}
