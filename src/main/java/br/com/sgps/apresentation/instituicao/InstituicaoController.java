package br.com.sgps.apresentation.instituicao;

import br.com.sgps.application.instituicao.*;
import br.com.sgps.common.pagination.Pagina;
import br.com.sgps.common.pagination.Paginacao;
import br.com.sgps.vaga.domain.entity.Instituicao;
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

    private final InstituicaoManagementApplicationService instituicaoService;
    private final InstituicaoOutputAssembler instituicaoAssembler;

    @GetMapping
    public List<InstituicaoOutPut> consultarTodos(){
        return instituicaoService.consultarTodos()
                .stream()
                .map(instituicaoAssembler::toOutput)
                .toList();
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

        return instituicaoService.buscar(instituicaoFiltro, paginacao);
    }

    @PostMapping
    public InstituicaoOutPut salvar(@RequestBody InstituicaoInput instituicaoInput){
        Instituicao instituicaoSalva =  instituicaoService.criar(instituicaoInput);

        return  new InstituicaoOutPut(instituicaoSalva);
    }

    @PutMapping("/{idInstituicao}")
    public InstituicaoOutPut alterar(@RequestBody InstituicaoInput instituicaoInput, @PathVariable String idInstituicao){

        return instituicaoAssembler.toOutput(instituicaoService.alterar(new InstituicaoId(UUID.fromString(idInstituicao)),instituicaoInput));
    }

    @GetMapping("/{idInstituicao}")
    public InstituicaoOutPut consultarPorId(@PathVariable String idInstituicao){
        Instituicao instituicao = instituicaoService.conusltarPorID(new InstituicaoId(UUID.fromString(idInstituicao)));

        return new InstituicaoOutPut(instituicao);
    }


}
