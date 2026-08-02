package br.com.sgps.common.pagination;

public record Paginacao(int pagina, int tamanho, String ordenadoPor, DirecaoOrdenacao direcao) {
}
