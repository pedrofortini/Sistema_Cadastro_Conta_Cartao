package com.cadastro.cliente.api.domain.cliente.converter;

import com.cadastro.cliente.api.application.exception.UnprocessableEntityException;
import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.ClienteService;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteRequestFixture;
import io.swagger.model.ClienteRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class ClienteRequestConverterTest {

    private ClienteRequestConverter converter;
    private ClienteService clienteService;

    @Before
    public void setUp() {

        this.clienteService = PowerMockito.mock(ClienteService.class);
        this.converter = new ClienteRequestConverter(this.clienteService);
    }

    @Test
    public void deveLancarUnprocessableEntityExceptionCasoClienteJaCadastradoComStatusEmAnalise(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        ClienteRequest request = ClienteRequestFixture.umClienteRequestFixture().build();

        PowerMockito.when(clienteService.buscaPorCpfCnpj("11752677687")).thenReturn(Optional.of(cliente));

        assertThatThrownBy(() -> converter.converte(request))
                .isInstanceOf(UnprocessableEntityException.class)
                .hasMessage(String.format("O Cliente de CPF/CNPJ %s ja possui uma solicitaçao de cadastro em analise ou aprovada",
                       request.getCpfCnpj()));
    }

    @Test
    public void deveLancarUnprocessableEntityExceptionCasoClienteJaCadastradoComStatusCriada(){

        Cliente cliente = ClienteFixture.umClienteFixture().comStatusContaCartaoCriada().build();
        ClienteRequest request = ClienteRequestFixture.umClienteRequestFixture().build();

        PowerMockito.when(clienteService.buscaPorCpfCnpj("11752677687")).thenReturn(Optional.of(cliente));

        assertThatThrownBy(() -> converter.converte(request))
                .isInstanceOf(UnprocessableEntityException.class)
                .hasMessage(String.format("O Cliente de CPF/CNPJ %s ja possui uma solicitaçao de cadastro em analise ou aprovada",
                        request.getCpfCnpj()));
    }

    @Test
    public void deveRetornarObjetoClienteComDadosRequestEStatusAnalisePendenteCasoClienteCadastradoComStatusRejeitada(){

        Cliente cliente = ClienteFixture.umClienteFixture().comStatusContaCartaoRejeitada().build();
        ClienteRequest request = ClienteRequestFixture.umClienteRequestFixture().build();

        PowerMockito.when(clienteService.buscaPorCpfCnpj("11752643690")).thenReturn(Optional.of(cliente));

        Cliente response = converter.converte(request);

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
    public void deveRetornarObjetoClienteComDadosRequestEStatusAnalisePendenteCasoClienteNaoEncontradoNaBase(){

        ClienteRequest request = ClienteRequestFixture.umClienteRequestFixture().build();

        PowerMockito.when(clienteService.buscaPorCpfCnpj("11752643690")).thenReturn(Optional.empty());

        Cliente response = converter.converte(request);

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
}