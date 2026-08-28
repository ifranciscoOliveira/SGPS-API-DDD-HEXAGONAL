package br.com.sgps.apresentation.exception;

import br.com.sgps.candidato.domain.exception.CandidatoNaoEncontratoException;
import br.com.sgps.shared.domain.exception.DocumentoEmUsoException;
import br.com.sgps.shared.domain.exception.EmailEmUsoException;
import br.com.sgps.shared.domain.exception.RecursoNaoEncontradoException;
import br.com.sgps.vaga.domain.exception.InstituicaoNaoEncontradoException;
import br.com.sgps.shared.domain.exception.NegocioException;
import br.com.sgps.vaga.domain.exception.VagaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErroResponse.CampoErro> campos = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .distinct()
                .map(campo -> {
                    String msg = ex.getBindingResult().getFieldErrors(campo)
                            .stream()
                            .map(FieldError::getDefaultMessage)
                            .findFirst()
                            .orElse("Valor inválido");
                    return new ErroResponse.CampoErro(campo, msg);
                })
                .toList();

        return new ErroResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação nos campos informados", campos);
    }

    @ExceptionHandler({
            RecursoNaoEncontradoException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponse handleNaoEncontrado(RuntimeException ex) {
        return new ErroResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler({
            NegocioException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResponse handleConflito(RuntimeException ex) {
        return new ErroResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResponse handleErroInterno(Exception ex) {
        ex.printStackTrace();
        return new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno. Tente novamente mais tarde.");
    }
}
