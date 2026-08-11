package br.com.sgps.apresentation.candidato;

import br.com.sgps.application.candidato.*;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.valueobject.CandidatoId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidato")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoManagementApplicationService candidatoManagementApplicationService;


    @GetMapping
    public List<CandidatoOutPut> consultarTodos() {
       return  candidatoManagementApplicationService.consultarTodos()
                .stream()
                .map(CandidatoOutPut::fromDomain)
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

        return  candidatoManagementApplicationService.buscar(candidatoFiltro, paginacao)
                .map(CandidatoOutPut::fromDomain);
    }


    @GetMapping("/{idCandidato}")
    public CandidatoOutPut consultarCandidatoPorId(String idCandidato) {

        Candidato candidato = candidatoManagementApplicationService.consultarPorId(
                new CandidatoId( UUID.fromString(idCandidato)));

        return CandidatoOutPut.fromDomain(candidato);


    }

    @PostMapping
    public CandidatoOutPut cadastrarCandidato(@RequestBody CandidateInput input) {
        Candidato candidatoSalvo = candidatoManagementApplicationService.criar(input);
        return CandidatoOutPut.fromDomain(candidatoSalvo);

    }

    @PutMapping("/{idCandidato}")
    public CandidatoOutPut alterarCandidato(@RequestBody CandidatoAlterarInput input, @PathVariable String idCandidato) {
        Candidato candidatoAlterado = candidatoManagementApplicationService.alterar(new CandidatoId(UUID.fromString(idCandidato)), input);
        return CandidatoOutPut.fromDomain(candidatoAlterado);
    }



}
