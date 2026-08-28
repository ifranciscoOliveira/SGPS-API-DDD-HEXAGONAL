package br.com.sgps.shared.domain.exception;

public class EmailEmUsoException extends NegocioException {

    public EmailEmUsoException(){super();}

    public EmailEmUsoException(String msg){
        super(msg);
    }
}
