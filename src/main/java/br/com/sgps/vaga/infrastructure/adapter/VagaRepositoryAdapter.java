package br.com.sgps.vaga.infrastructure.adapter;

import br.com.sgps.vaga.application.filter.VagaFiltro;
import br.com.sgps.common.pagination.Pagina;
import br.com.sgps.common.pagination.Paginacao;
import br.com.sgps.infrastructure.entity.VagaPersistenceEntity;
import br.com.sgps.vaga.infrastructure.specification.VagaSpecification;
import br.com.sgps.vaga.application.port.out.VagaRepositoryPort;
import br.com.sgps.vaga.domain.entity.Vaga;
import br.com.sgps.vaga.domain.valueobject.VagaId;
import br.com.sgps.vaga.infrastructure.assembler.VagaPersistenceEntityAssembler;
import br.com.sgps.vaga.infrastructure.persistence.VagaPersistenceRepository;
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
public class VagaRepositoryAdapter implements VagaRepositoryPort {

    private final VagaPersistenceRepository vagaPersistenceRepository;
    private final VagaPersistenceEntityAssembler vagaPersistenceEntityAssembler;


    @Override
    public Vaga persistir(Vaga vaga) {
        UUID id = vaga.id().value();
        vagaPersistenceRepository.findById(id)
                .ifPresentOrElse((vagaEncontrada) ->
                                alterar(vaga, vagaEncontrada),
                        () -> salvar(vaga));
        return vaga;
    }

    @Override
    public List<Vaga> consultarTodos() {
        return vagaPersistenceRepository.findAll()
                .stream()
                .map(vagaPersistenceEntityAssembler::persistenceEntityToDomain)
                .toList();
    }

    @Override
    public Pagina<Vaga> listar(VagaFiltro filtro, Paginacao paginacao) {
        Pageable pageable =
                PageRequest.of(
                        paginacao.pagina(),
                        paginacao.tamanho()
                );

        Page page = vagaPersistenceRepository.findAll(VagaSpecification.filtrar(filtro),pageable)
                .map(vagaPersistenceEntityAssembler::persistenceEntityToDomain);

        return new Pagina<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber()
        );
    }

    @Override
    public Optional<Vaga> buscarPorId(VagaId id) {
        Optional<VagaPersistenceEntity> vagaPersistenceEntity = vagaPersistenceRepository.findById(id.value());
        return Optional.of(vagaPersistenceEntityAssembler.persistenceEntityToDomain(vagaPersistenceEntity.orElse(null)));
    }

    @Override
    public void remover(VagaId id) {
        vagaPersistenceRepository.deleteById(id.value());
    }

    @Override
    public boolean existeTituloCadastrado(String titulo, VagaId id) {
        return vagaPersistenceRepository.existTituloCadastrado(titulo,id.value());
    }

    @Override
    public boolean existe(VagaId id) {
        return vagaPersistenceRepository.existsById(id.value());
    }

    private void alterar(Vaga vaga, VagaPersistenceEntity vagaEncontrada) {
        VagaPersistenceEntity vagaPersistenceEntity = vagaPersistenceEntityAssembler.fromDomain(vaga);
        vagaPersistenceEntity.setId(vagaEncontrada.getId());
        vagaPersistenceRepository.saveAndFlush(vagaPersistenceEntity);
    }

    private void salvar(Vaga vaga) {
        VagaPersistenceEntity vagaPersistenceEntity = vagaPersistenceEntityAssembler.fromDomain(vaga);
        vagaPersistenceRepository.saveAndFlush(vagaPersistenceEntity);

    }
}
