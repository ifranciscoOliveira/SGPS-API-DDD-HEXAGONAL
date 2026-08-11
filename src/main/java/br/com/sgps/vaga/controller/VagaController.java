package br.com.sgps.vaga.controller;

import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.application.dto.VagaInput;
import br.com.sgps.vaga.application.dto.VagaOutPut;
import br.com.sgps.vaga.application.port.in.AtualizarVagaUseCase;
import br.com.sgps.vaga.application.port.in.CadastrarVagaUseCase;
import br.com.sgps.vaga.application.port.in.ConsultarTodasVagasUseCase;
import br.com.sgps.vaga.application.port.in.ListarVagasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vaga")
@RequiredArgsConstructor
public class VagaController {

    private final ConsultarTodasVagasUseCase consultarTodasVagasUseCase;
    private final ListarVagasUseCase listarVagasUseCase;
    private final CadastrarVagaUseCase cadastrarVagaUseCase;
    private final AtualizarVagaUseCase atualizarVagaUseCase;


    @GetMapping
    public List<VagaOutPut> consultarTodos(){
        return consultarTodasVagasUseCase.consultarTodasVagas()
                .stream()
                .toList();
    }

    @GetMapping("/buscar")
    public Pagina<VagaOutPut> buscar(VagaFiltro vagaFiltro, Pageable pageable){

        Paginacao paginacao  = new Paginacao(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString(), null);
        return listarVagasUseCase.listarVagas(vagaFiltro, paginacao);
    }

    @PostMapping
    public VagaOutPut salvar(@RequestBody VagaInput vagaInput){
        return cadastrarVagaUseCase.cadastrar(vagaInput);
    }

    @PutMapping
    public VagaOutPut alterar(@RequestBody VagaInput vagaAlterarInput, @PathVariable String id){
        return atualizarVagaUseCase.alterar(UUID.fromString(id), vagaAlterarInput);
    }
}
