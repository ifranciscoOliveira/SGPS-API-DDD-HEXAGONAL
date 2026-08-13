package br.com.sgps.shared.domain.exception;


public class NegocioException extends RuntimeException {

    public NegocioException(){
        super();
    }
    public NegocioException(String msg){
        super(msg);
    }
}
