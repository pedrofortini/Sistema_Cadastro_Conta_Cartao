package com.cadastro.cliente.api.infrastructure.mensageria;

import com.cadastro.cliente.api.domain.mensagemsolicitacao.MensagemSolicitacaoCadastroCliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaService {

    private final Producer<Long, MensagemSolicitacaoCadastroCliente> kafkaProducer;

    public KafkaService() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "cadastro-conta-cartao");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "300000");


        kafkaProducer = new KafkaProducer<Long, MensagemSolicitacaoCadastroCliente>(
                props,
                new LongSerializer(),
                new KafkaJsonSerializer(new ObjectMapper()));
    }


    public void enviaMensagem(MensagemSolicitacaoCadastroCliente res) {

        kafkaProducer.send(new ProducerRecord<>(
                "cadastro-conta-cartao-analise-documentos-topic", res.getIdSolicitacao(),  res));
    }
}
