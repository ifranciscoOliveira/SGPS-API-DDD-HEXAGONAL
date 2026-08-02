package br.com.sgps.domain.repository;


import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.infrastructure.assembler.VagaPersistenceEntityAssembler;
import br.com.sgps.infrastructure.provider.InstituicaoPersistenceProvider;
import br.com.sgps.infrastructure.provider.VagaPersistenceProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

@DataJpaTest
@Import({
        VagaPersistenceProvider.class,
        InstituicaoPersistenceProvider.class,
        VagaPersistenceEntityAssembler.class})
class VagaRepositoryDomainTestIT {

    private  final VagaRepositoryDomain vagaRepositoryDomain;
    private final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    @Autowired
    public VagaRepositoryDomainTestIT(VagaRepositoryDomain vagaRepositoryDomain,
                                      InstituicaoRepositoryDomain instituicaoRepositoryDomain){
        this.vagaRepositoryDomain = vagaRepositoryDomain;
        this.instituicaoRepositoryDomain = instituicaoRepositoryDomain;

    }


    @BeforeEach
    void before(){
    }

    @Test
    public void deveCadastrarComSucesso(){
        var instituicao = Instituicao.criarNovaInstituicao("Instituição Teste",
                new Documento("12.345.678/0001-90"),
                "21334456786",
                new Email("teste@teste.com"));

        instituicaoRepositoryDomain.persistir(instituicao);


        Vaga vaga = Vaga.criarNovaVaga(
                "Vaga de Desenvolvedor",
                "Descrição da vaga de desenvolvedor",
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(30),
                5,
                "ATIVA",
                "Observação da vaga",
                instituicao.id()
        );
        vagaRepositoryDomain.persistir(vaga);

    }




}