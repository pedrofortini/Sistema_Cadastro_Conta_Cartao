package com.cadastro.cliente.api.domain.mensagemsolicitacao;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.MensagemSolicitacaoCadastroClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.SolicitacaoFixture;
import com.cadastro.cliente.api.domain.mensagemsolicitacao.converter.MensagemSolicitacaoCadastroClienteConverter;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.StatusSolicitacao;
import com.cadastro.cliente.api.infrastructure.mensageria.KafkaService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class EnvioMensagemSolicitacaoCadastroServiceTest {

    private EnvioMensagemSolicitacaoCadastroService service;

    private KafkaService kafkaService;
    private MensagemSolicitacaoCadastroClienteConverter converter;

    @Before
    public void setUp() {

        this.kafkaService = PowerMockito.mock(KafkaService.class);
        this.converter = PowerMockito.mock(MensagemSolicitacaoCadastroClienteConverter.class);

        this.service = new EnvioMensagemSolicitacaoCadastroService(kafkaService, converter);
    }

    @Test
    public void deveExecutarMetodosConverteEEnviaMensagemCorretamenteAoEnviarMensagemDeSolicitacaoDeCadastro(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();
        MensagemSolicitacaoCadastroCliente mensagem = MensagemSolicitacaoCadastroClienteFixture
                .umMensagemSolicitacaoCadastroClienteFixture().build();

        PowerMockito.when(converter.converte(cliente, solicitacao)).thenReturn(mensagem);

        service.enviaMensagemSolicitacaoCadastro(cliente, solicitacao);

        Mockito.verify(converter).converte(cliente, solicitacao);
        Mockito.verify(kafkaService).enviaMensagem(mensagem);
    }

}