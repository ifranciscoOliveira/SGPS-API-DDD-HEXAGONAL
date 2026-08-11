package br.com.sgps.vaga.application.port.in.instituicao;

import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.filter.InstituicaoFiltro;

public interface ListarInstituicaoUseCase {

    Pagina<InstituicaoOutPut> buscar(InstituicaoFiltro instituicaoFiltro, Paginacao paginacao);
}
