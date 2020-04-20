package com.cadastro.cliente.api.infrastructure.mensageria;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.MensagemSolicitacaoCadastroClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.SolicitacaoFixture;
import com.cadastro.cliente.api.domain.mensagemsolicitacao.MensagemSolicitacaoCadastroCliente;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.*;

public class KafkaServiceTest {

    private KafkaService service;
    private Producer<Long, MensagemSolicitacaoCadastroCliente> kafkaProducer;

    @Before
    public void setUp() {

        this.service = new KafkaService();
        this.kafkaProducer = PowerMockito.mock(Producer.class);

        Whitebox.setInternalState(this.service, "kafkaProducer", this.kafkaProducer);
    }

    @Test
    public void deveExecutarMetodoSendDoKafkaProducerAoEnviarMensagemSolicitacaoCadatroCliente(){

        MensagemSolicitacaoCadastroCliente mensagem = MensagemSolicitacaoCadastroClienteFixture
                .umMensagemSolicitacaoCadastroClienteFixture().build();

        service.enviaMensagem(mensagem);

        Mockito.verify(kafkaProducer).send(new ProducerRecord<>(
                "cadastro-conta-cartao-analise-documentos-topic", mensagem.getIdSolicitacao(),  mensagem));
    }
}