package br.com.sgps.vaga.domain.service;

import br.com.sgps.shared.domain.annotation.DomainService;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.domain.exception.DocumentoEmUsoException;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.vaga.domain.exception.InstituicaoNaoEncontradoException;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class InstituicaoServiceDomain {

    private final InstituicaoRepositoryPort instituicaoRepositoryPort;

    public Instituicao salvar(String nome, Documento cnpjCpf,
                              String telefone, Email email){

        Instituicao instituicao = Instituicao.criarNovaInstituicao(nome,cnpjCpf,telefone,email);

        validarDocumentoEmailEmUso(instituicao);
        return instituicao;

    }

    public Instituicao alterar(InstituicaoId id, String nome, Documento cpfCnpj,
                               String telefone, Email email){

        Instituicao instituicao = instituicaoRepositoryPort.consultarPorId(id).orElseThrow(InstituicaoNaoEncontradoException::new);
        instituicao.alterarNome(nome);
        instituicao.alterarTelefone(telefone);
        instituicao.alterarEmail(email);
        validarDocumentoEmailEmUso(instituicao);
        return instituicao;

    }

    private void validarDocumentoEmailEmUso(Instituicao instituicao) {
        if(instituicaoRepositoryPort.existeDocumentoCadastrado(instituicao.cnpjCpf(), instituicao.id())){
            throw new DocumentoEmUsoException("Já existe uma instituição com o CNPJ/CPF informado.");
        }
        if(instituicaoRepositoryPort.existeEmailCadastrado(instituicao.email(), instituicao.id())){
            throw new EmailEmUsoException("Já existe email cadastro.");
        }
    }






}
