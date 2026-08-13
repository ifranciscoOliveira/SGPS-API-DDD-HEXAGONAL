package br.com.sgps.inscricao.infrastructure.adapter;

import br.com.sgps.inscricao.application.port.out.InscrticaoRepositoryPort;
import br.com.sgps.inscricao.domain.entity.Inscricao;
import br.com.sgps.inscricao.infrastructure.assembler.InscricaoPersistenceEntityAssembler;
import br.com.sgps.inscricao.infrastructure.entity.InscricaoPersistenceEntity;
import br.com.sgps.inscricao.infrastructure.persistence.InscricaoPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InscrticaoRepositoryAdapter  implements InscrticaoRepositoryPort {

    private final InscricaoPersistenceRepository  inscricaoPersistenceRepository;
    private final InscricaoPersistenceEntityAssembler inscricaoPersistenceEntityAssembler;

    @Override
    public void persistir(Inscricao inscricao) {
        UUID id = inscricao.id().value();

        inscricaoPersistenceRepository.findById(id)
                .ifPresentOrElse((inscricaoEncontrada) ->
                                alterar(inscricao, inscricaoEncontrada),
                        () -> salvar(inscricao));
    }

    @Override
    public boolean existeInscricaoPorCandidatoEPorVaga(UUID candidatoId, UUID vagaId) {
        return inscricaoPersistenceRepository.existsByCandidatoIdAndVagaId(candidatoId, vagaId);
    }

    @Override
    public Inscricao buscarPorId(UUID inscricaoId) {
        return inscricaoPersistenceRepository.findById(inscricaoId)
                .map(inscricaoPersistenceEntityAssembler::fromPersistenceEntity)
                .orElse(null);
    }

    private void salvar(Inscricao inscricao) {
        InscricaoPersistenceEntity persistenteEntity =
                inscricaoPersistenceEntityAssembler.fromDomain(inscricao);
        inscricaoPersistenceRepository.save(persistenteEntity);
    }

    private void alterar(Inscricao inscricao, InscricaoPersistenceEntity inscricaoEncontrada) {
        InscricaoPersistenceEntity persistenteEntity =
                inscricaoPersistenceEntityAssembler.merge(inscricaoEncontrada, inscricao);
        inscricaoPersistenceRepository.save(persistenteEntity);
    }
}
