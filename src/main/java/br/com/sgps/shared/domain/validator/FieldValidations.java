package br.com.sgps.shared.domain.validator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidations {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);


    private FieldValidations() {

    }

    public static void requiresNonBlank(String value) {
        requiresNonBlank(value, "");
    }

    public static void requiresNonBlank(String value, String errorMessage) {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public static void requiresValidEmail(String email) {
        requiresValidEmail(email, null);
    }

    public static void requiresValidEmail(String email, String errorMessage) {
        Objects.requireNonNull(email, errorMessage);
        if (email.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (!emailValido(email)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static boolean emailValido(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();


    }
}
