package br.com.sgps.shared.paginacao;

public record Paginacao(int pagina, int tamanho, String ordenadoPor, DirecaoOrdenacao direcao) {
}
