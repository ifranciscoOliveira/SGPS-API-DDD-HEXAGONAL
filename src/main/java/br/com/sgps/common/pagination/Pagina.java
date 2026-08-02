package br.com.sgps.common.pagination;

import java.util.List;
import java.util.function.Function;

public record Pagina<T>(List<T> itens, long totalItems, long totalPaginas, long paginaAtual) {

    public <R> Pagina<R> map(
            Function<T, R> mapper){

        return new Pagina<>(
                itens.stream()
                        .map(mapper)
                        .toList(),
                totalItems,
                totalPaginas,
                paginaAtual
        );
    }
}
