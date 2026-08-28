package br.com.sgps.candidato.domain.exception;

import br.com.sgps.shared.domain.exception.RecursoNaoEncontradoException;

public class CandidatoNaoEncontratoException extends RecursoNaoEncontradoException {

    public CandidatoNaoEncontratoException() {

        super();
    }
    public CandidatoNaoEncontratoException(String message) {

        super(message);
    }
}
