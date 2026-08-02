package br.com.sgps.vaga.domain.exception;

public class VagaNaoEncontradaException extends RuntimeException {

    public VagaNaoEncontradaException() {

        super();
    }
    public VagaNaoEncontradaException(String message) {

        super(message);
    }
}
