package com.cadastro.cliente.api.domain.cliente.fixture;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.StatusSolicitacao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SolicitacaoFixture {

    private Cliente cliente;
    private Long id;
    private Date dataCriacao;
    private StatusSolicitacao status;
    private Double latitude;
    private Double longitude;
    private String nomeImagemRg;
    private String nomeImagemCpfCnpj;

    private SolicitacaoFixture(){

        this.cliente = ClienteFixture.umClienteFixture().build();
        this.id = 1L;
        this.latitude = -3.45;
        this.longitude = -6.79;
        this.nomeImagemCpfCnpj = "CPF_CNPJ_Pedro_Fortini";
        this.nomeImagemRg = "RG_Pedro_Fortini";
        this.dataCriacao = new Date();
        this.status = StatusSolicitacao.EM_ANALISE_DOCUMENTOS;
    }

    public static SolicitacaoFixture umSolicitacaoFixture(){

        return new SolicitacaoFixture();
    }

    public Solicitacao build(){

        Solicitacao solicitacao = new Solicitacao();

        solicitacao.setCliente(this.cliente);
        solicitacao.setId(this.id);
        solicitacao.setStatus(this.status);
        solicitacao.setDataCriacao(this.dataCriacao);
        solicitacao.setLatitude(this.latitude);
        solicitacao.setLongitude(this.longitude);
        solicitacao.setNomeImagemCpfCnpj(this.nomeImagemCpfCnpj);
        solicitacao.setNomeImagemRg(this.nomeImagemRg);

        return solicitacao;
    }

}
