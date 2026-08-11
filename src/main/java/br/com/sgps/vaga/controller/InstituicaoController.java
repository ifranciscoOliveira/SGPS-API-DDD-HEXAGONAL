package br.com.sgps.vaga.controller;

import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.filter.InstituicaoFiltro;
import br.com.sgps.vaga.application.port.in.instituicao.*;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/instituicao")
@RequiredArgsConstructor
public class InstituicaoController {

    private final CadastrarInstituicaoUseCase cadastrarInstituicaoUseCase;
    private final AtualizarInstituicaoUseCase  atualizarInstituicaoUseCase;
    private final ConsultarInstituicaoPorIdUseCase consultarInstituicaoPorIdUseCase;
    private final ConsultarTodasInstituicaoUseCase consultarTodasInstituicaoUseCase;
    private final ListarInstituicaoUseCase  listarInstituicaoUseCase;


    @GetMapping
    public List<InstituicaoOutPut> consultarTodos(){
        return consultarTodasInstituicaoUseCase.consultarTodos();
    }

    @GetMapping("/buscar")
    public Pagina<InstituicaoOutPut> buscar(InstituicaoFiltro instituicaoFiltro, Pageable pageable){

        Paginacao paginacao =
                new Paginacao(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSort()
                                .toString(),
                        null
                );

        return listarInstituicaoUseCase.buscar(instituicaoFiltro, paginacao);
    }

    @PostMapping
    public InstituicaoOutPut salvar(@RequestBody InstituicaoInput instituicaoInput){
        return  cadastrarInstituicaoUseCase.criar(instituicaoInput);
    }

    @PutMapping("/{idInstituicao}")
    public InstituicaoOutPut alterar(@RequestBody InstituicaoInput instituicaoInput, @PathVariable String idInstituicao){

        return atualizarInstituicaoUseCase.alterar(new InstituicaoId(UUID.fromString(idInstituicao)),instituicaoInput);
    }

    @GetMapping("/{idInstituicao}")
    public InstituicaoOutPut consultarPorId(@PathVariable String idInstituicao){
        return consultarInstituicaoPorIdUseCase.conusltarPorID(new InstituicaoId(UUID.fromString(idInstituicao)));
    }


}
