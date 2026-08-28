package br.com.sgps.vaga.domain.exception;

import br.com.sgps.shared.domain.exception.RecursoNaoEncontradoException;

public class VagaNaoEncontradaException extends RecursoNaoEncontradoException {

    public VagaNaoEncontradaException() {

        super();
    }
    public VagaNaoEncontradaException(String message) {

        super(message);
    }
}
