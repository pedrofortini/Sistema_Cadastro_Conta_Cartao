package com.cadastro.cliente.api.domain.cliente;

import com.cadastro.cliente.api.application.exception.PersistenceException;
import com.cadastro.cliente.api.application.exception.RecursoNaoEncontradoException;
import com.cadastro.cliente.api.application.exception.UnprocessableEntityException;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteRequestFixture;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.SolicitacaoCadastroService;
import com.cadastro.cliente.api.domain.solicitacaocadastro.StatusSolicitacao;
import com.cadastro.cliente.api.infrastructure.persistence.ClienteRepository;
import io.swagger.model.ClienteRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class ClienteServiceTest {

    private ClienteService service;
    private ClienteRepository clienteRepository;
    private SolicitacaoCadastroService solicitacaoCadastroService;

    @Before
    public void setUp() {

        this.clienteRepository = PowerMockito.mock(ClienteRepository.class);
        this.solicitacaoCadastroService = PowerMockito.mock(SolicitacaoCadastroService.class);

        this.service = new ClienteService(this.clienteRepository, this.solicitacaoCadastroService);
    }

    @Test
    public void deveExecutarMetodoFindByIdDoClienteRepositoryAoBuscarClientePorCpfCnpj(){

        service.buscaPorCpfCnpj("11752677687");
        Mockito.verify(this.clienteRepository).findById("11752677687");
    }

    @Test
    public void deveLancarPersistenceExceptionCasoOcorraProblemaAoSalvarDadosCliente(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(clienteRepository.save(cliente)).thenThrow(new RuntimeException());

        assertThatThrownBy(() -> service.salvaCliente(cliente))
                .isInstanceOf(PersistenceException.class)
                .hasMessage(String.format("Erro ao persistir dados do Cliente de CPF/CNPJ %s",
                        cliente.getCpfCnpj()));
    }

    @Test
    public void deveChamarMetodoSaveDoClienteRepositoryAoSalvarDadosClienteComSucesso(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(clienteRepository.save(cliente)).thenReturn(cliente);

        service.salvaCliente(cliente);
        Mockito.verify(clienteRepository).save(cliente);
    }

    @Test
    public void deveChamarMetodoRegistraSolicitacaoCadastroClienteDosolicitacaoCadastroServiceAoSalvarDadosClienteComSucesso(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(clienteRepository.save(cliente)).thenReturn(cliente);

        service.salvaCliente(cliente);
        Mockito.verify(solicitacaoCadastroService).registraSolicitacaoCadastroCliente(cliente);
    }

    @Test
    public void deveRetornarClienteCorretoAoSalvarDadosClienteComSucesso(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente response = service.salvaCliente(cliente);

        assertThat(response.getCpfCnpj()).isEqualTo("11752677687");
        assertThat(response.getNome()).isEqualTo("Pedro Teste");
        assertThat(response.getNumeroRG()).isEqualTo("16556789");
        assertThat(response.getCidade()).isEqualTo("Belo Horizonte");
        assertThat(response.getEstado()).isEqualTo("Minas Gerais");
        assertThat(response.getPais()).isEqualTo("Brasil");
        assertThat(response.getLatitude()).isEqualTo(-3.45);
        assertThat(response.getLongitude()).isEqualTo(-6.79);
        assertThat(response.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(response.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
        assertThat(response.getStatusContaCartao()).isEqualTo(StatusContaCartao.ANALISE_PENDENTE);
        assertThat(response.getContaCartao()).isNull();
    }

    @Test
    public void deveLancarRecursoNaoEncontradoExceptionCasoOcorraProblemaAoBuscarDadosCliente(){

        PowerMockito.when(clienteRepository.findById("11752677687")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscaDadosCliente("11752677687"))
                .isInstanceOf(RecursoNaoEncontradoException.class)
                .hasMessage(String.format("Nao foi possivel encontrar o cliente com CPF/CNPJ %s",
                        "11752677687"));
    }

    @Test
    public void deveRetornarClienteCorretoAoBuscarDadosClienteComSucesso(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(clienteRepository.findById("11752677687")).thenReturn(Optional.of(cliente));

        Cliente response = service.buscaDadosCliente("11752677687");

        assertThat(response.getCpfCnpj()).isEqualTo("11752677687");
        assertThat(response.getNome()).isEqualTo("Pedro Teste");
        assertThat(response.getNumeroRG()).isEqualTo("16556789");
        assertThat(response.getCidade()).isEqualTo("Belo Horizonte");
        assertThat(response.getEstado()).isEqualTo("Minas Gerais");
        assertThat(response.getPais()).isEqualTo("Brasil");
        assertThat(response.getLatitude()).isEqualTo(-3.45);
        assertThat(response.getLongitude()).isEqualTo(-6.79);
        assertThat(response.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(response.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
        assertThat(response.getStatusContaCartao()).isEqualTo(StatusContaCartao.ANALISE_PENDENTE);
        assertThat(response.getContaCartao()).isNull();
    }

    @Test
    public void deveLancarRecursoNaoEncontradoExceptionCasoOcorraProblemaAoBuscarDadosClienteParaAtualizacaoParcial(){

        PowerMockito.when(clienteRepository.findById("11752677687")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizacaoParcialCliente("11752677687", null, null))
                .isInstanceOf(RecursoNaoEncontradoException.class)
                .hasMessage(String.format("Nao foi possivel encontrar o cliente com CPF/CNPJ %s",
                        "11752677687"));
    }

    @Test
    public void deveExecutarMetodoSaveDoClienteRepositoryComParametroCorretoCasoEntradaCorreta(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        PowerMockito.when(clienteRepository.findById("11752677687")).thenReturn(Optional.of(cliente));

        ArgumentCaptor acCliente = ArgumentCaptor.forClass(Cliente.class);

        service.atualizacaoParcialCliente("11752677687", "CRIADA", "123456");

        Mockito.verify(clienteRepository).save((Cliente) acCliente.capture());
        Cliente parametroCliente = (Cliente) acCliente.getValue();

        assertThat(parametroCliente.getCpfCnpj()).isEqualTo("11752677687");
        assertThat(parametroCliente.getNome()).isEqualTo("Pedro Teste");
        assertThat(parametroCliente.getNumeroRG()).isEqualTo("16556789");
        assertThat(parametroCliente.getCidade()).isEqualTo("Belo Horizonte");
        assertThat(parametroCliente.getEstado()).isEqualTo("Minas Gerais");
        assertThat(parametroCliente.getPais()).isEqualTo("Brasil");
        assertThat(parametroCliente.getLatitude()).isEqualTo(-3.45);
        assertThat(parametroCliente.getLongitude()).isEqualTo(-6.79);
        assertThat(parametroCliente.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(parametroCliente.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
        assertThat(parametroCliente.getStatusContaCartao()).isEqualTo(StatusContaCartao.CRIADA);
        assertThat(parametroCliente.getContaCartao()).isEqualTo("123456");
    }

    @Test
    public void deveRetornarClienteCorretoAoFazerAtualizacaoParcialDosDadosDoClienteComSucesso(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        cliente.setContaCartao("123456");
        cliente.setStatusContaCartao(StatusContaCartao.CRIADA);

        PowerMockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
        PowerMockito.when(clienteRepository.findById("11752677687")).thenReturn(Optional.of(cliente));

        Cliente response = service.atualizacaoParcialCliente("11752677687", "CRIADA", "123456");

        assertThat(response.getCpfCnpj()).isEqualTo("11752677687");
        assertThat(response.getNome()).isEqualTo("Pedro Teste");
        assertThat(response.getNumeroRG()).isEqualTo("16556789");
        assertThat(response.getCidade()).isEqualTo("Belo Horizonte");
        assertThat(response.getEstado()).isEqualTo("Minas Gerais");
        assertThat(response.getPais()).isEqualTo("Brasil");
        assertThat(response.getLatitude()).isEqualTo(-3.45);
        assertThat(response.getLongitude()).isEqualTo(-6.79);
        assertThat(response.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(response.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
        assertThat(response.getStatusContaCartao()).isEqualTo(StatusContaCartao.CRIADA);
        assertThat(response.getContaCartao()).isEqualTo("123456");
    }
}