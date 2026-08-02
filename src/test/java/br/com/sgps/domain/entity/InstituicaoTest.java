package br.com.sgps.domain.entity;

import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.domain.entity.Instituicao;
import org.junit.jupiter.api.Test;


class InstituicaoTest {

    @Test
    void deveValidarDocumentoInvalido(){

        org.assertj.core.api.Assertions.assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() -> Instituicao.criarNovaInstituicao("Toldos LTDA",
                        new Documento("01.23.9.4.8.21"),
                        "818222932",
                        new Email("teste@teste.com")));

    }



}