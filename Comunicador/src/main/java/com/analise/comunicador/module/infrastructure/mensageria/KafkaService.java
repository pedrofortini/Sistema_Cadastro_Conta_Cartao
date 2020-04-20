package com.analise.comunicador.module.infrastructure.mensageria;

import com.analise.comunicador.module.application.ServiceConstants;
import com.analise.comunicador.module.domain.mensagemcomunicacao.MensagemComunicacao;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;


@Service
public class KafkaService {

    private final Consumer<String, MensagemComunicacao> kafkaComunicacoesConsumer;

    public KafkaService() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("enable.auto.commit", "true");
        props.put("group.id", "cadastro-conta-cartao");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "300000");
        
        kafkaComunicacoesConsumer = new KafkaConsumer<String, MensagemComunicacao>(props, new StringDeserializer(),
                new KafkaJsonDeserializer<MensagemComunicacao>(MensagemComunicacao.class));
    }
    
    public Consumer criaConsumidorTopicoComunicacoes() {

        kafkaComunicacoesConsumer.subscribe(Collections.singletonList(
                ServiceConstants.NOME_TOPICO_COMUNICACAO));
        return kafkaComunicacoesConsumer;
    }
}
