package com.analise.credito.module.infrastructure.mensageria;

import com.analise.credito.module.application.ServiceConstants;
import com.analise.credito.module.domain.mensagemanalisecredito.MensagemAnaliseCreditoCliente;
import com.analise.credito.module.domain.mensagemcomunicacao.MensagemComunicacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;


@Service
public class KafkaService {

    private final Producer<String, MensagemComunicacao> kafkaComunicacaoProducer;

    private final Consumer<Long, MensagemAnaliseCreditoCliente> kafkaAnaliseCreditoConsumer;

    public KafkaService() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("enable.auto.commit", "true");
        props.put("group.id", "cadastro-conta-cartao");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "300000");

        kafkaComunicacaoProducer = new KafkaProducer<String, MensagemComunicacao>(
                props,
                new StringSerializer(),
                new KafkaJsonSerializer(new ObjectMapper()));

        kafkaAnaliseCreditoConsumer = new KafkaConsumer<Long, MensagemAnaliseCreditoCliente>(props, new LongDeserializer(),
                new KafkaJsonDeserializer<MensagemAnaliseCreditoCliente>(MensagemAnaliseCreditoCliente.class));
    }

    public void enviaMensagemComunicacao(MensagemComunicacao msg) {

        kafkaComunicacaoProducer.send(new ProducerRecord<>(
                ServiceConstants.NOME_TOPICO_COMUNICACAO, msg.getIdComunicacao(),  msg));
    }

    public Consumer criaConsumidorTopicoAnaliseCredito() {

        kafkaAnaliseCreditoConsumer.subscribe(Collections.singletonList(
                ServiceConstants.NOME_TOPICO_ANALISE_CREDITO));
        return kafkaAnaliseCreditoConsumer;
    }
}
