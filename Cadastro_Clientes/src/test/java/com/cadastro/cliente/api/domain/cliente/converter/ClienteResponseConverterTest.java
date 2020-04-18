package com.cadastro.cliente.api.domain.cliente.converter;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteRequestFixture;
import io.swagger.model.ClienteRequest;
import io.swagger.model.ClienteResponse;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ClienteResponseConverterTest {

    private ClienteResponseConverter converter;

    @Before
    public void setUp() {

        this.converter = new ClienteResponseConverter();
    }

    @Test
    public void deveRetornarObjetoClienteResponseComDadosCorretosDoObjetoDeEntrada(){

        Cliente cliente = ClienteFixture.umClienteFixture().comStatusContaCartaoRejeitada().build();

        ClienteResponse response = converter.converte(cliente);

        assertThat(response.getCpfCnpj()).isEqualTo("11752677687");
        assertThat(response.getNome()).isEqualTo("Pedro Teste");
        assertThat(response.getCidade()).isEqualTo("Belo Horizonte");
        assertThat(response.getEstado()).isEqualTo("Minas Gerais");
        assertThat(response.getPais()).isEqualTo("Brasil");
        assertThat(response.getNumeroRg()).isEqualTo("16556789");
        assertThat(response.getContaCartao()).isNull();
        assertThat(response.getStatusCriacaoConta()).isEqualTo(StatusContaCartao.REJEITADA.name());
    }

}