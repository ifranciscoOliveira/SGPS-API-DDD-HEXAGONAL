package br.com.sgps.apresentation.instituicao;

import br.com.sgps.vaga.application.dto.InstituicaoInput;
import br.com.sgps.vaga.application.dto.InstituicaoOutPut;
import br.com.sgps.vaga.application.assembler.InstituicaoOutputAssembler;
import br.com.sgps.vaga.controller.InstituicaoController;
import br.com.sgps.vaga.domain.entity.Instituicao;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.vaga.domain.valueobject.InstituicaoId;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InstituicaoController.class)
class InstituicaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private InstituicaoManagementApplicationService instituicaoService;

    @MockitoBean
    private InstituicaoOutputAssembler instituicaoAssembler;

    @Test
    void deveConsultarTodasAsInstituicoes() throws Exception {
        Instituicao instituicao = criarInstituicao("11111111-1111-1111-1111-111111111111");
        InstituicaoOutPut output = new InstituicaoOutPut(instituicao);

        when(instituicaoService.consultarTodos()).thenReturn(List.of(instituicao));
        when(instituicaoAssembler.toOutput(instituicao)).thenReturn(output);

        mockMvc.perform(get("/instituicao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(output.getId()))
                .andExpect(jsonPath("$[0].nome").value(output.getNome()))
                .andExpect(jsonPath("$[0].cnpjCpf").value(output.getCnpjCpf()))
                .andExpect(jsonPath("$[0].telefone").value(output.getTelefone()))
                .andExpect(jsonPath("$[0].email").value(output.getEmail()));
    }

    @Test
    void deveSalvarInstituicao() throws Exception {
        InstituicaoInput input = new InstituicaoInput(
                "Instituicao Alpha",
                "529.982.247-25",
                "81999999999",
                "contato@alpha.com"
        );
        Instituicao instituicao = criarInstituicao("22222222-2222-2222-2222-222222222222");

        when(instituicaoService.criar(any(InstituicaoInput.class))).thenReturn(instituicao);

        mockMvc.perform(post("/instituicao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(instituicao.id().value().toString()))
                .andExpect(jsonPath("$.nome").value(instituicao.nome()))
                .andExpect(jsonPath("$.cnpjCpf").value(instituicao.cnpjCpf().value()))
                .andExpect(jsonPath("$.telefone").value(instituicao.telefone()))
                .andExpect(jsonPath("$.email").value(instituicao.email().value()));
    }

    @Test
    void deveConsultarInstituicaoPorId() throws Exception {
        UUID id = UUID.fromString("33333333-3333-3333-3333-333333333333");
        Instituicao instituicao = criarInstituicao(id.toString());

        when(instituicaoService.conusltarPorID(new InstituicaoId(id))).thenReturn(instituicao);

        mockMvc.perform(get("/instituicao/{idInstituicao}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value(instituicao.nome()))
                .andExpect(jsonPath("$.cnpjCpf").value(instituicao.cnpjCpf().value()))
                .andExpect(jsonPath("$.telefone").value(instituicao.telefone()))
                .andExpect(jsonPath("$.email").value(instituicao.email().value()));
    }

    @Test
    void deveAlterarInstituicao() throws Exception {
        UUID id = UUID.fromString("44444444-4444-4444-4444-444444444444");
        InstituicaoInput input = new InstituicaoInput(
                "Instituicao Beta",
                "529.982.247-25",
                "81988888888",
                "contato@beta.com"
        );
        Instituicao instituicao = criarInstituicao(id.toString());
        InstituicaoOutPut output = new InstituicaoOutPut(instituicao);

        when(instituicaoService.alterar(eq(new InstituicaoId(id)), any(InstituicaoInput.class))).thenReturn(instituicao);
        when(instituicaoAssembler.toOutput(instituicao)).thenReturn(output);

        mockMvc.perform(put("/instituicao/{idInstituicao}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(output.getId()))
                .andExpect(jsonPath("$.nome").value(output.getNome()))
                .andExpect(jsonPath("$.cnpjCpf").value(output.getCnpjCpf()))
                .andExpect(jsonPath("$.telefone").value(output.getTelefone()))
                .andExpect(jsonPath("$.email").value(output.getEmail()));
    }

    private Instituicao criarInstituicao(String id) {
        return Instituicao.criarExistente()
                .id(new InstituicaoId(UUID.fromString(id)))
                .nome("Instituicao Teste")
                .cnpjCpf(new Documento("529.982.247-25"))
                .telefone("81999999999")
                .email(new Email("teste@sgps.com"))
                .build();
    }
}
