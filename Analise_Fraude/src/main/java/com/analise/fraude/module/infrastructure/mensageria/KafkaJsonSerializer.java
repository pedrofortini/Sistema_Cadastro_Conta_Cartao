package com.analise.fraude.module.infrastructure.mensageria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaJsonSerializer implements Serializer {

    private ObjectMapper objectMapper;

    private Logger logger;

    public KafkaJsonSerializer(ObjectMapper objectMapper){

        this.objectMapper = objectMapper;
        this.logger = LoggerFactory.getLogger(KafkaJsonSerializer.class);
    }

    @Override
    public void configure(Map map, boolean b) {
    }

    @Override
    public void close() {
    }

    @Override
    public byte[] serialize(String s, Object o) {

        byte[] retVal = null;

        try {
            retVal = objectMapper.writeValueAsBytes(o);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        return retVal;
    }
}
