package com.cadastro.cliente.api.application.controllers;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.ClienteService;
import com.cadastro.cliente.api.domain.cliente.converter.ClienteRequestConverter;
import com.cadastro.cliente.api.domain.cliente.converter.ClienteResponseConverter;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteRequestFixture;
import io.swagger.model.ClienteRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ClienteControllerTest {

    private ClienteController controller;

    private ClienteRequestConverter clienteRequestConverter;
    private ClienteResponseConverter clienteResponseConverter;
    private ClienteService clienteService;

    @Before
    public void setUp() {

        this.clienteRequestConverter = PowerMockito.mock(ClienteRequestConverter.class);
        this.clienteResponseConverter = PowerMockito.mock(ClienteResponseConverter.class);
        this.clienteService = PowerMockito.mock(ClienteService.class);

        this.controller = new ClienteController(this.clienteRequestConverter,
                this.clienteResponseConverter, this.clienteService);
    }

    @Test
    public void deveExecutarMetodoAtualizacaoParcialClienteDoClienteServiceAoAtualizarClienteParcialmente(){

        controller.atualizaStatusCliente("11752677687", "CRIADA", "123456");

        Mockito.verify(this.clienteService).atualizacaoParcialCliente("11752677687",
                "CRIADA", "123456");
    }

    @Test
    public void deveRetornarResponseEntityNoContentAoAtualizarClienteParcialmente(){

        ResponseEntity responseEntity = controller.atualizaStatusCliente("11752677687", "CRIADA", "123456");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deveExecutarMetodosBuscaDadosClienteEConverteAoObterDadosDoCliente(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(this.clienteService.buscaDadosCliente("11752677687")).thenReturn(cliente);

        controller.obtemDadosCliente("11752677687");

        Mockito.verify(this.clienteService).buscaDadosCliente("11752677687");
        Mockito.verify(this.clienteResponseConverter).converte(cliente);
    }

    @Test
    public void deveRetornarResponseEntityOKAoObterDadosDoCliente(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        PowerMockito.when(this.clienteService.buscaDadosCliente("11752677687")).thenReturn(cliente);

        ResponseEntity responseEntity = controller.obtemDadosCliente("11752677687");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deveExecutarMetodosConverteESalvaClienteAoSalvarDadosDoCliente(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        ClienteRequest request = ClienteRequestFixture.umClienteRequestFixture().build();

        PowerMockito.when(this.clienteRequestConverter.converte(request)).thenReturn(cliente);

        controller.salvaCliente(request);

        Mockito.verify(this.clienteRequestConverter).converte(request);
        Mockito.verify(this.clienteService).salvaCliente(cliente);
    }

    @Test
    public void deveRetornarResponseEntityOKAoSalvarDadosDoCliente(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        ClienteRequest request = ClienteRequestFixture.umClienteRequestFixture().build();

        PowerMockito.when(this.clienteRequestConverter.converte(request)).thenReturn(cliente);

        ResponseEntity responseEntity = controller.salvaCliente(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}