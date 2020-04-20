package com.cadastro.cliente.api.infrastructure.mensageria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class KafkaJsonSerializerTest {

    private KafkaJsonSerializer serializer;
    private ObjectMapper mapper;
    private Logger logger;

    @Before
    public void setUp() {

        mapper = PowerMockito.mock(ObjectMapper.class);
        logger = PowerMockito.mock(Logger.class);

        this.serializer = new KafkaJsonSerializer(mapper);
    }

    @Test
    public void deveRetornarNuloCasoOcorraExcecaoNaSerializacaoDosDados() throws JsonProcessingException {

        PowerMockito.when(this.mapper.writeValueAsBytes(Mockito.any())).thenThrow(new RuntimeException("teste"));

        byte[] response = this.serializer.serialize(null, "teste");

        assertThat(response).isNull();
    }

    @Test
    public void deveRetornarDadosCorretosCasoSucessoNaSerializacaoDosDados() throws JsonProcessingException {

        PowerMockito.when(this.mapper.writeValueAsBytes(Mockito.any())).thenReturn("teste".getBytes());

        byte[] response = this.serializer.serialize(null, "teste");

        assertThat(response).isNotNull();
    }
}