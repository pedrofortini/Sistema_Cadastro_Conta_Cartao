package com.cadastro.cliente.api.domain.solicitacaocadastro;

import com.cadastro.cliente.api.application.exception.DataFormatoInvalidoException;
import com.cadastro.cliente.api.application.exception.PersistenceException;
import com.cadastro.cliente.api.application.exception.RecursoNaoEncontradoException;
import com.cadastro.cliente.api.application.util.DateUtil;
import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.SolicitacaoFixture;
import com.cadastro.cliente.api.infrastructure.persistence.SolicitacaoCadastroRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class SolicitacaoCadastroServiceTest {

    private SolicitacaoCadastroService service;
    private SolicitacaoCadastroRepository solicitacaoCadastroRepository;

    @Before
    public void setUp() {

        this.solicitacaoCadastroRepository = PowerMockito.mock(SolicitacaoCadastroRepository.class);
        this.service = new SolicitacaoCadastroService(this.solicitacaoCadastroRepository);
    }

    @Test
    public void deveLancarPersistenceExceptionCasoOcorraProblemaAoSalvarSolicitacaoCadastroDoCliente(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(solicitacaoCadastroRepository.save(Mockito.any())).thenThrow(new RuntimeException());

        assertThatThrownBy(() -> service.registraSolicitacaoCadastroCliente(cliente))
                .isInstanceOf(PersistenceException.class)
                .hasMessage(String.format("Erro ao persistir dados da Solicitaçao de Cadastro do Cliente de CPF/CNPJ %s",
                        cliente.getCpfCnpj()));
    }

    @Test
    public void deveExecutarMetodoSaveDoSolicitacaoCadastroRepositoryComParametroCorretoCasoEntradaCorreta(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        ArgumentCaptor acSolicitacao = ArgumentCaptor.forClass(Solicitacao.class);

        service.registraSolicitacaoCadastroCliente(cliente);

        Mockito.verify(solicitacaoCadastroRepository).save((Solicitacao) acSolicitacao.capture());
        Solicitacao parametroSolicitacao = (Solicitacao) acSolicitacao.getValue();

        assertThat(parametroSolicitacao.getCliente().getCpfCnpj()).isEqualTo("11752677687");
        assertThat(parametroSolicitacao.getDataCriacao()).isBeforeOrEqualsTo(new Date());
        assertThat(parametroSolicitacao.getStatus()).isEqualTo(StatusSolicitacao.EM_ANALISE_DOCUMENTOS);
        assertThat(parametroSolicitacao.getLatitude()).isEqualTo(-3.45);
        assertThat(parametroSolicitacao.getLongitude()).isEqualTo(-6.79);
        assertThat(parametroSolicitacao.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(parametroSolicitacao.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
    }

    @Test
    public void deveRetornarSolicitacaoCadastroCorretaCasoEntradaCorreta(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();

        PowerMockito.when(solicitacaoCadastroRepository.save(Mockito.any(Solicitacao.class))).thenReturn(solicitacao);

        Solicitacao response = service.registraSolicitacaoCadastroCliente(cliente);

        assertThat(response.getCliente().getCpfCnpj()).isEqualTo("11752677687");
        assertThat(response.getDataCriacao()).isBeforeOrEqualsTo(new Date());
        assertThat(response.getStatus()).isEqualTo(StatusSolicitacao.EM_ANALISE_DOCUMENTOS);
        assertThat(response.getLatitude()).isEqualTo(-3.45);
        assertThat(response.getLongitude()).isEqualTo(-6.79);
        assertThat(response.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(response.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
    }

    @Test
    public void deveLancarRecursoNaoEncontradoExceptionCasoOcorraProblemaAoBuscarDadosSolicitacaoParaAtualizacaoParcial(){

        PowerMockito.when(solicitacaoCadastroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizacaoParcialSolicitacaoCliente(1L, null))
                .isInstanceOf(RecursoNaoEncontradoException.class)
                .hasMessage(String.format("Nao foi possivel encontrar a solicitaçao de cadastro de identificador %s",
                        1L));
    }

    @Test
    public void deveExecutarMetodoSaveDoSolicitacaoCadastroRepositoryComParametroCorretoParaAtualizacaoParcial(){

        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();
        PowerMockito.when(solicitacaoCadastroRepository.findById(1L)).thenReturn(Optional.of(solicitacao));

        ArgumentCaptor acSolicitacao = ArgumentCaptor.forClass(Solicitacao.class);

        service.atualizacaoParcialSolicitacaoCliente(1L, "APROVADA");

        Mockito.verify(solicitacaoCadastroRepository).save((Solicitacao) acSolicitacao.capture());
        Solicitacao parametroSolicitacao = (Solicitacao) acSolicitacao.getValue();

        assertThat(parametroSolicitacao.getLatitude()).isEqualTo(-3.45);
        assertThat(parametroSolicitacao.getLongitude()).isEqualTo(-6.79);
        assertThat(parametroSolicitacao.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(parametroSolicitacao.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
        assertThat(parametroSolicitacao.getCliente().getCpfCnpj()).isEqualTo("11752677687");
        assertThat(parametroSolicitacao.getDataCriacao()).isBeforeOrEqualsTo(new Date());
        assertThat(parametroSolicitacao.getStatus()).isEqualTo(StatusSolicitacao.APROVADA);
    }

    @Test
    public void deveRetornarSolicitacaoCadastroCorretaAoFazerAtualizacaoParcialDosDadosDaSolicitacaoComSucesso(){

        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();
        solicitacao.setStatus(StatusSolicitacao.APROVADA);

        PowerMockito.when(solicitacaoCadastroRepository.findById(1L)).thenReturn(Optional.of(solicitacao));
        PowerMockito.when(solicitacaoCadastroRepository.save(Mockito.any(Solicitacao.class))).thenReturn(solicitacao);

        Solicitacao response = service.atualizacaoParcialSolicitacaoCliente(1L, "APROVADA");

        assertThat(response.getLatitude()).isEqualTo(-3.45);
        assertThat(response.getLongitude()).isEqualTo(-6.79);
        assertThat(response.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(response.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
        assertThat(response.getCliente().getCpfCnpj()).isEqualTo("11752677687");
        assertThat(response.getDataCriacao()).isBeforeOrEqualsTo(new Date());
        assertThat(response.getStatus()).isEqualTo(StatusSolicitacao.APROVADA);
    }

    @Test
    public void deveLancarDataFormatoInvalidoExceptionCasoDataInicialNulaAoBuscarSolicitacoesDoClienteNoIntervalo(){

        assertThatThrownBy(() -> service.buscaSolicitacoesDoClienteNoIntervalo(null, null, null))
                .isInstanceOf(DataFormatoInvalidoException.class)
                .hasMessage(String.format("Erro ao processar data %s",
                        null));
    }

    @Test
    public void deveLancarDataFormatoInvalidoExceptionCasoOcorraProblemaAoConverterDataInicialOuFinalAoBuscarSolicitacoesDoClienteNoIntervalo(){

        assertThatThrownBy(() -> service.buscaSolicitacoesDoClienteNoIntervalo(null, "2020-19-08", null))
                .isInstanceOf(DataFormatoInvalidoException.class)
                .hasMessage(String.format("Erro ao processar data %s",
                        "2020-19-08"));
    }

    @Test
    public void deveRetornarListaSolicitacoesNoIntevaloBuscadoCasoHajaSucessoNaBusca(){

        Cliente cliente = new Cliente();
        String dataInicial = "13/04/2020";
        String dataFinal = "20/04/2020";

        List<Solicitacao> solicitacoes = new ArrayList<>();
        solicitacoes.add(SolicitacaoFixture.umSolicitacaoFixture().build());

        PowerMockito.when(solicitacaoCadastroRepository.findByClienteAndDataCriacaoBetween(cliente,
                DateUtil.stringToDate(dataInicial), DateUtil.stringToDate(dataFinal)))
                .thenReturn(solicitacoes);

        List<Solicitacao> response = service.buscaSolicitacoesDoClienteNoIntervalo(
                cliente, dataInicial, dataFinal);

        assertThat(response).isNotEmpty();
        assertThat(response.get(0).getId()).isEqualTo(1L);
    }
}