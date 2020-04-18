package com.cadastro.cliente.api.application.controllers;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.ClienteService;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.SolicitacaoFixture;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.SolicitacaoCadastroService;
import com.cadastro.cliente.api.domain.solicitacaocadastro.converter.SolicitacaoCadastroResponseConverter;
import io.swagger.model.SolicitacaoCadastroResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class SolicitacaoControllerTest {

    private SolicitacaoController controller;

    private SolicitacaoCadastroService solicitacaoCadastroService;
    private SolicitacaoCadastroResponseConverter converter;
    private ClienteService clienteService;

    @Before
    public void setUp() {

        this.solicitacaoCadastroService = PowerMockito.mock(SolicitacaoCadastroService.class);
        this.converter = PowerMockito.mock(SolicitacaoCadastroResponseConverter.class);
        this.clienteService = PowerMockito.mock(ClienteService.class);

        this.controller = new SolicitacaoController(this.solicitacaoCadastroService, this.clienteService,
                this.converter);
    }

    @Test
    public void deveExecutarMetodoAtualizacaoParcialClienteDoSolicitacaoCadastroClienteServiceAoAtualizarSolicitacaoParcialmente(){

        controller.atualizaStatusSolicitacaoCliente(1L, "APROVADA");

        Mockito.verify(this.solicitacaoCadastroService).atualizacaoParcialSolicitacaoCliente(1L,
                "APROVADA");
    }

    @Test
    public void deveRetornarResponseEntityNoContentAoAtualizarSolicitacaoParcialmente(){

        ResponseEntity responseEntity = controller.atualizaStatusSolicitacaoCliente(1L, "APROVADA");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deveExecutarMetodosBuscaDadosClienteBuscarSolicitacoesDoClienteNoIntervaloEConverteListaSolicitacoesAoObterSolicitacoesDoClienteNoIntervalo(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();
        List<Solicitacao> solicitacoes = Arrays.asList(solicitacao);

        List<SolicitacaoCadastroResponse> response = new ArrayList<>();

        PowerMockito.when(this.clienteService.buscaDadosCliente("11752677687")).thenReturn(cliente);
        PowerMockito.when(this.solicitacaoCadastroService.buscaSolicitacoesDoClienteNoIntervalo(cliente,
                "13/04/2020", "20/04/2020")).thenReturn(Arrays.asList(solicitacao));
        PowerMockito.when(this.converter.converteListaSolicitacoes(solicitacoes)).thenReturn(response);

        controller.obtemSolicitacoesClienteNoPeriodo("11752677687",
                "13/04/2020", "20/04/2020");

        Mockito.verify(this.clienteService).buscaDadosCliente("11752677687");
        Mockito.verify(this.solicitacaoCadastroService).buscaSolicitacoesDoClienteNoIntervalo(cliente,
                "13/04/2020", "20/04/2020");
        Mockito.verify(this.converter).converteListaSolicitacoes(solicitacoes);
    }

    @Test
    public void deveRetornarResponseEntityOKAoObterSolicitacoesDoClienteNoIntervalor(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();

        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();
        List<Solicitacao> solicitacoes = Arrays.asList(solicitacao);

        List<SolicitacaoCadastroResponse> response = new ArrayList<>();

        PowerMockito.when(this.clienteService.buscaDadosCliente("11752677687")).thenReturn(cliente);
        PowerMockito.when(this.solicitacaoCadastroService.buscaSolicitacoesDoClienteNoIntervalo(cliente,
                "13/04/2020", "20/04/2020")).thenReturn(Arrays.asList(solicitacao));
        PowerMockito.when(this.converter.converteListaSolicitacoes(solicitacoes)).thenReturn(response);

        ResponseEntity responseEntity = controller.obtemSolicitacoesClienteNoPeriodo("11752677687",
                "13/04/2020", "20/04/2020");

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}