package com.cadastro.cliente.api.domain.solicitacaocadastro.converter;

import com.cadastro.cliente.api.application.util.DateUtil;
import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;
import com.cadastro.cliente.api.domain.cliente.fixture.ClienteFixture;
import com.cadastro.cliente.api.domain.cliente.fixture.SolicitacaoFixture;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.StatusSolicitacao;
import io.swagger.model.ClienteResponse;
import io.swagger.model.SolicitacaoCadastroResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class SolicitacaoCadastroResponseConverterTest {

    private SolicitacaoCadastroResponseConverter converter;

    @Before
    public void setUp() {

        this.converter = new SolicitacaoCadastroResponseConverter();
    }

    @Test
    public void deveRetornarListaDeSolicitacaoCadastroResponseComDadosCorretosDoObjetoDeEntrada(){

        Solicitacao solicitacao = SolicitacaoFixture.umSolicitacaoFixture().build();

        List<SolicitacaoCadastroResponse> response = converter.converteListaSolicitacoes(Arrays.asList(solicitacao));

        assertThat(response).isNotEmpty();
        assertThat(response.get(0).getCpfCnpjCliente()).isEqualTo("11752677687");
        assertThat(response.get(0).getDataCriacao()).isEqualTo(DateUtil.dateToString(solicitacao.getDataCriacao()));
        assertThat(response.get(0).getId()).isEqualTo(1L);
        assertThat(response.get(0).getLatitude()).isEqualTo(-3.45);
        assertThat(response.get(0).getLongitude()).isEqualTo(-6.79);
        assertThat(response.get(0).getNomeImagemCpfCnpj()).isEqualTo("CPF_CNPJ_Pedro_Fortini");
        assertThat(response.get(0).getNomeImagemRg()).isEqualTo("RG_Pedro_Fortini");
        assertThat(response.get(0).getStatusSolicitacao()).isEqualTo(StatusSolicitacao.EM_ANALISE_DOCUMENTOS.name());
    }
}