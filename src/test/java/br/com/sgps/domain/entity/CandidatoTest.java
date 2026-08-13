package br.com.sgps.domain.entity;


import br.com.sgps.candidato.domain.entity.Candidato;
import br.com.sgps.shared.domain.exception.NegocioException;
import br.com.sgps.shared.domain.valueobject.Documento;
import br.com.sgps.shared.domain.valueobject.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

class CandidatoTest {

    private Candidato criarCandidato(){
       return  Candidato.criarNovoCandidato(
                new Documento("573.049.640-06"),
                "FULANO DE TAL",
                new Email("teste@teste.com"),
                "81991102392",
                LocalDate.of(1985,01,01));
    }


    @Test
    void deveCriarCandidatoComSucesso(){
        var cpf = new Documento("573.049.640-06");
        Candidato candidato =  Candidato.criarNovoCandidato(
                cpf,
                "FULANO DE TAL",
                new Email("teste@teste.com"),
                "81991102392",
                LocalDate.of(1985,01,01));

        Assertions.assertNotNull(candidato);
        Assertions.assertNotNull(candidato.id());
        Assertions.assertEquals(cpf.value(), candidato.cpf().value());


    }

    @Test
    void deveValidarMaioridadeDoCandidato(){
        Assertions.assertThrows(NegocioException.class, () ->
                    Candidato.criarNovoCandidato(
                            new Documento("573.049.640-06"),
                            "FULANO DE TAL",
                            new Email("teste@teste.com"),
                            "81991102392",
                            LocalDate.of(2009,01,01)));


    }

    @ParameterizedTest
    @ValueSource(strings ={
            "111.111.111.11","222.222.222.22","333.333.333.33",
            "444.444.444.44","555.555.555.55","666.666.666-66",
            "777.777.777-77","888.888.888-88","999.999.999-99",
            "000.000.000-00", "113.343.34.21", "111.222s.222-ff",
            "adasdad asdasdasd d","123.34#@.222-55"
    })
    void deveValidarDocumentoCpf(String cpf){

        Assertions.assertThrows(NegocioException.class, () ->
                Candidato.criarNovoCandidato(
                        new Documento(cpf),
                        "FULANO DE TAL",
                        new Email("teste@teste.com"),
                        "81991102392",
                        LocalDate.of(2000,01,01)));
    }



}