package br.com.sgps.shared.domain.valueobject;

import br.com.sgps.shared.domain.validator.FieldValidations;

public record Email(String value) {

    public Email {
        FieldValidations.requiresValidEmail(value);
    }



}
