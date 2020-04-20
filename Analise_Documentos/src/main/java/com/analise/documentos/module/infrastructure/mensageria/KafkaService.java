package com.analise.documentos.module.infrastructure.mensageria;

import com.analise.documentos.module.application.ServiceConstants;
import com.analise.documentos.module.domain.mensagemanalisedocumentos.MensagemAnaliseDocumentosCliente;
import com.analise.documentos.module.domain.mensagemanalisefraude.MensagemAnaliseFraude;
import com.analise.documentos.module.domain.mensagemcomunicacao.MensagemComunicacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;


@Service
public class KafkaService {

    private final Producer<Long, MensagemAnaliseFraude> kafkaAnaliseFraudeProducer;
    private final Producer<String, MensagemComunicacao> kafkaComunicacaoProducer;

    private final Consumer<Long, MensagemAnaliseDocumentosCliente> kafkaAnaliseDocumentosConsumer;

    public KafkaService() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("enable.auto.commit", "true");
        props.put("group.id", "cadastro-conta-cartao");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "300000");


        kafkaAnaliseFraudeProducer = new KafkaProducer<Long, MensagemAnaliseFraude>(
                props,
                new LongSerializer(),
                new KafkaJsonSerializer(new ObjectMapper()));

        kafkaComunicacaoProducer = new KafkaProducer<String, MensagemComunicacao>(
                props,
                new StringSerializer(),
                new KafkaJsonSerializer(new ObjectMapper()));

        kafkaAnaliseDocumentosConsumer = new KafkaConsumer<Long, MensagemAnaliseDocumentosCliente>(props, new LongDeserializer(),
                new KafkaJsonDeserializer<MensagemAnaliseDocumentosCliente>(MensagemAnaliseDocumentosCliente.class));
    }


    public void enviaMensagemAnaliseFraude(MensagemAnaliseFraude msg) {

        kafkaAnaliseFraudeProducer.send(new ProducerRecord<>(
                ServiceConstants.NOME_TOPICO_ANALISE_FRAUDE, msg.getIdSolicitacao(),  msg));
    }

    public void enviaMensagemComunicacao(MensagemComunicacao msg) {

        kafkaComunicacaoProducer.send(new ProducerRecord<>(
                ServiceConstants.NOME_TOPICO_COMUNICACAO, msg.getIdComunicacao(),  msg));
    }

    public Consumer criaConsumidorTopicoAnaliseDocumentos() {

        kafkaAnaliseDocumentosConsumer.subscribe(Collections.singletonList(
                ServiceConstants.NOME_TOPICO_ANALISE_DOCUMENTOS));
        return kafkaAnaliseDocumentosConsumer;
    }
}
