package br.com.sgps.vaga.application.port.in;

import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.application.dto.VagaOutPut;

public interface ListarVagasUseCase {

    Pagina<VagaOutPut> listarVagas(VagaFiltro vagaFiltro, Paginacao paginacao);
}
