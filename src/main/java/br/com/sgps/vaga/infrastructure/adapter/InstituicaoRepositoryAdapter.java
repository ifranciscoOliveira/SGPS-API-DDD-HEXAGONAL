package br.com.sgps.vaga.infrastructure.adapter;

import br.com.sgps.vaga.application.filter.InstituicaoFiltro;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.infrastructure.entity.InstituicaoPersistenceEntity;
import br.com.sgps.shared.paginacao.Pagina;
import br.com.sgps.shared.paginacao.Paginacao;
import br.com.sgps.vaga.application.port.out.InstituicaoRepositoryPort;
import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import br.com.sgps.vaga.infrastructure.assembler.InstituicaoPersistenceEntityAssembler;
import br.com.sgps.vaga.infrastructure.persistence.InstituicaoPersistenceRporitoy;
import br.com.sgps.vaga.infrastructure.specification.InstituicaoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InstituicaoRepositoryAdapter implements InstituicaoRepositoryPort {

    private final InstituicaoPersistenceRporitoy instituicaoPersistenceRporitoy;
    private final InstituicaoPersistenceEntityAssembler instituicaoPersistenceEntityAssembler;



    @Override
    public boolean existeDocumentoCadastrado(Documento documento, InstituicaoId id) {
        return instituicaoPersistenceRporitoy.existCpfCadastrado(documento.value(), id.value());
    }

    @Override
    public boolean existeEmailCadastrado(Email email, InstituicaoId id) {
        return instituicaoPersistenceRporitoy.existEmailCadastrado(email.value(), id.value());
    }

    @Override
    public boolean existe(InstituicaoId id) {
        return instituicaoPersistenceRporitoy.existsById(id.value());
    }

    @Override
    public void persistir(Instituicao instituicao) {
        UUID id = instituicao.id().value();

        instituicaoPersistenceRporitoy.findById(id)
                .ifPresentOrElse((instituicaoEncontrada) ->
                                alterar(instituicao, instituicaoEncontrada),
                        () -> salvar(instituicao));
    }

    private void salvar(Instituicao instituicao) {
        InstituicaoPersistenceEntity persistenteEntity = new InstituicaoPersistenceEntity();
        persistenteEntity.setId(instituicao.id().value());
        persistenteEntity.setNome(instituicao.nome());
        persistenteEntity.setCnpjCpf(instituicao.cnpjCpf().value());
        persistenteEntity.setTelefone(instituicao.telefone());
        persistenteEntity.setEmail(instituicao.email().value());

        instituicaoPersistenceRporitoy.saveAndFlush(persistenteEntity);
    }

    private void alterar(Instituicao instituicao, InstituicaoPersistenceEntity instituicaoEncontrada) {

        instituicaoEncontrada.setNome(instituicao.nome());
        instituicaoEncontrada.setCnpjCpf(instituicao.cnpjCpf().value());
        instituicaoEncontrada.setTelefone(instituicao.telefone());
        instituicaoEncontrada.setEmail(instituicao.email().value());

        instituicaoPersistenceRporitoy.saveAndFlush(instituicaoEncontrada);
    }

    @Override
    public Optional<Instituicao> consultarPorId(InstituicaoId id) {
        InstituicaoPersistenceEntity instituicaoRepository =
                instituicaoPersistenceRporitoy.findById(id.value()).orElse(null);
        if (instituicaoRepository != null) {
            return Optional.of(instituicaoPersistenceEntityAssembler.toDomain(instituicaoRepository));
        }
        return Optional.empty();
    }

    @Override
    public List<Instituicao> listarTodos() {
        List<InstituicaoPersistenceEntity>  lista = instituicaoPersistenceRporitoy.findAll();
        return instituicaoPersistenceEntityAssembler.persistenceEntityToDomain(lista);
    }

    @Override
    public Pagina<Instituicao> buscar(InstituicaoFiltro instituicaoFiltro, Paginacao paginacao) {

        Pageable pageable =
                PageRequest.of(
                        paginacao.pagina(),
                        paginacao.tamanho()
                );


        Page<Instituicao> page =   instituicaoPersistenceRporitoy
                .findAll(InstituicaoSpecification.filtrar(instituicaoFiltro),pageable)
                .map(instituicaoPersistenceEntityAssembler::toDomain);

        return new Pagina<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber()
        );
    }
}
