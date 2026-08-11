package br.com.sgps.shared.domain.annotation;


import java.lang.annotation.*;

/**
 * Anotação criada com o intuito de anotar os servicesDomain registrar
 *  e com isso possibilitar que os serviços de domínio possam ficar disponivesi Spring sem
 * precisar colocar @Service neles.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DomainService {
}
