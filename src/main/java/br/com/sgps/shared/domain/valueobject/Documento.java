package br.com.sgps.shared.domain.valueobject;

import br.com.sgps.shared.domain.exception.NegocioException;
import br.com.sgps.shared.domain.validator.FieldValidations;
import org.apache.logging.log4j.util.Strings;

public record Documento(String value) {

    public enum Tipo {

        CPF(11),
        CNPJ(14);

        private final int tamanho;

        Tipo(int tamanho) {
            this.tamanho = tamanho;
        }

        public int getTamanho() {
            return tamanho;
        }
    }

    public Documento(String value){
        FieldValidations.requiresNonBlank(value);
        String documentoSemFormatacao = removerFormatacao(value);
        validar(documentoSemFormatacao);
        this.value = documentoSemFormatacao;
    }

    private void validar(String valor) throws NegocioException {

        if (valor.length() != Tipo.CPF.getTamanho() &&  valor.length() != Tipo.CNPJ.getTamanho() ){
            throw new NegocioException("Documento inválido");
        }
        if(valor.length() == Tipo.CPF.getTamanho()){
            validarCpf(valor);
        }

        if(valor.length() == Tipo.CNPJ.getTamanho()){
            validarCnpj(valor);
        }

    }
    private String removerFormatacao(String value) {
        return value.replaceAll("\\D", "");
    }

    private void validarCpf(String cpf) {
        if (!cpfValido(cpf)) {
            throw new NegocioException("CPF inválido");
        }
    }

    private void validarCnpj(String cnpj) {
        if (!cnjValido(cnpj)) {
            throw new NegocioException("CNPJ inválido");
        }
    }

    private boolean cpfValido(String cpf){

        if(Strings.isBlank(cpf)){
            return false;
        }
        String cpfSoNumeros = cpf.replaceAll("\\D","");

        if(cpfSoNumeros.length() != 11 || cpfSoNumeros.matches("(\\d)\\1{10}")
        || cpfSoNumeros.matches(".*\\p{L}.*")){
            return false;
        }


        try {
            // Cálculo do 1º dígito
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int dig1 = 11 - (soma % 11);
            dig1 = (dig1 >= 10) ? 0 : dig1;

            // Cálculo do 2º dígito
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int dig2 = 11 - (soma % 11);
            dig2 = (dig2 >= 10) ? 0 : dig2;

            // Verifica se os dígitos batem
            return dig1 == (cpf.charAt(9) - '0') &&
                    dig2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }

    }

    private boolean cnjValido(String cnpj){
        if(Strings.isBlank(cnpj)){
            return false;
        }
        String cnpjSoNumeros = cnpj.replaceAll("\\D","");

        if(cnpjSoNumeros.length() != 14|| cnpjSoNumeros.matches("(\\d)\\1{13}")
                || cnpjSoNumeros.matches(".*\\p{L}.*")){
            return false;
        }

        int dig1 = calcularDigito(cnpjSoNumeros, 12);
        int dig2 = calcularDigito(cnpjSoNumeros, 13);

        return dig1 == (cnpjSoNumeros.charAt(12) - '0') &&
                dig2 == (cnpjSoNumeros.charAt(13) - '0');
    }

    private static int calcularDigito(String cnpj, int tamanho) {
        int[] pesos = (tamanho == 12)
                ? new int[]{5,4,3,2,9,8,7,6,5,4,3,2}
                : new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2};

        int soma = 0;

        for (int i = 0; i < tamanho; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos[i];
        }

        int resto = soma % 11;

        return (resto < 2) ? 0 : 11 - resto;
    }




}
