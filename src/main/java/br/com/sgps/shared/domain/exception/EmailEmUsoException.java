package br.com.sgps.shared.domain.exception;

public class EmailEmUsoException extends RuntimeException {

    public EmailEmUsoException(){super();}

    public EmailEmUsoException(String msg){
        super(msg);
    }
}
