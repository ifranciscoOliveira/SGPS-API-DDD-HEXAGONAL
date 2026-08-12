package br.com.sgps.candidato.domain.exception;

public class CandidatoNaoEncontratoException extends RuntimeException {

    public CandidatoNaoEncontratoException() {

        super();
    }
    public CandidatoNaoEncontratoException(String message) {

        super(message);
    }
}
