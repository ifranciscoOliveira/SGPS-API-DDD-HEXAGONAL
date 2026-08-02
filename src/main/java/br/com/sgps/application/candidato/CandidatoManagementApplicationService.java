package br.com.sgps.application.candidato;

import br.com.sgps.common.pagination.Pagina;
import br.com.sgps.common.pagination.Paginacao;
import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.exception.CandidatoNaoEncontratoException;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.service.CandidatoService;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CandidatoManagementApplicationService {

    private final CandidatoService candidatoServiceDomain;
    private final CandidatoRepositoryDomain candidatoRepositoryDomain;

    @Transactional
    public Candidato criar(CandidateInput candidatoInput) throws EmailEmUsoException {
        Objects.requireNonNull(candidatoInput);

        Candidato candidato = candidatoServiceDomain.salvar(new Documento(candidatoInput.getCpf()),
                candidatoInput.getNome(),new Email(candidatoInput.getEmail()),
                candidatoInput.getTelefone(),candidatoInput.getDataNascimento());

        candidatoRepositoryDomain.persistir(candidato);
        return candidato;
    }

    @Transactional
    public Candidato alterar(CandidatoId id,CandidatoAlterarInput candidatoAlterarInput ){

        Objects.requireNonNull(id);
        Objects.requireNonNull(candidatoAlterarInput);

        Candidato candidatoAlterar = candidatoServiceDomain.alterar(id,candidatoAlterarInput.getNome(),
                new Email(candidatoAlterarInput.getEmail()),
                candidatoAlterarInput.getTelefone(),candidatoAlterarInput.getDataNascimento());

        candidatoRepositoryDomain.persistir(candidatoAlterar);

        return candidatoAlterar;

    }

    @Transactional(readOnly = true)
    public Candidato consultarPorId(CandidatoId id) {
        Objects.requireNonNull(id);
        return candidatoRepositoryDomain.consultarPorId(id)
                .orElseThrow(CandidatoNaoEncontratoException::new);
    }

    @Transactional(readOnly = true)
    public List<Candidato> consultarTodos() {
        return candidatoRepositoryDomain.consultarTodos();
    }

    @Transactional(readOnly = true)
    public Pagina<Candidato> buscar(CandidatoFiltro candidatoFiltro, Paginacao paginacao) {
        return candidatoRepositoryDomain.buscar(candidatoFiltro, paginacao);
    }



}
