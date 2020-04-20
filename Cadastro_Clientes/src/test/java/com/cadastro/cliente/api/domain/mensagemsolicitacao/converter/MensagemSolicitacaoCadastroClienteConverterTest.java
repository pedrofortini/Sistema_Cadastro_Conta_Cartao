package com.cadastro.cliente.api.domain.mensagemsolicitacao.converter;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.SolicitacaoFixture;
import com.cadastro.cliente.api.domain.mensagemsolicitacao.MensagemSolicitacaoCadastroCliente;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import io.swagger.model.ClienteResponse;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MensagemSolicitacaoCadastroClienteConverterTest {

    private MensagemSolicitacaoCadastroClienteConverter converter;

    @Before
    public void setUp() {

        this.converter = new MensagemSolicitacaoCadastroClienteConverter();
    }

    @Test
    public void deveRetornarObjetoMensagemSolicitacaoCadastroClienteComDadosCorretosDoObjetoDeEntrada(){

        Cliente cliente = ClienteFixture.umClienteFixture().build();
        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();

        MensagemSolicitacaoCadastroCliente mensagem = converter.converte(cliente, solicitacao);

        assertThat(mensagem.getIdSolicitacao()).isEqualTo(1L);
        assertThat(mensagem.getCpfCnpjCliente()).isEqualTo("11752677687");
        assertThat(mensagem.getNumeroRGCliente()).isEqualTo("16556789");
        assertThat(mensagem.getNomeCliente()).isEqualTo("Pedro Teste");
        assertThat(mensagem.getDataCriacao()).isEqualTo("19/04/2020");
        assertThat(mensagem.getLatitude()).isEqualTo(-3.45);
        assertThat(mensagem.getLongitude()).isEqualTo(-6.79);
        assertThat(mensagem.getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(mensagem.getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
    }
}