package br.com.sgps.shared.domain.exception;

public class DocumentoEmUsoException extends RuntimeException {

    public DocumentoEmUsoException() {
        super();
    }

    public DocumentoEmUsoException(String message) {
        super(message);
    }
}
