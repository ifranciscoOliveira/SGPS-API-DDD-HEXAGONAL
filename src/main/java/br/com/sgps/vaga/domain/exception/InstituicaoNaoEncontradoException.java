package br.com.sgps.vaga.domain.exception;

import br.com.sgps.shared.domain.exception.RecursoNaoEncontradoException;

public class InstituicaoNaoEncontradoException extends RecursoNaoEncontradoException {

    public InstituicaoNaoEncontradoException() {
    }

    public InstituicaoNaoEncontradoException(String message) {
        super(message);
    }
}
