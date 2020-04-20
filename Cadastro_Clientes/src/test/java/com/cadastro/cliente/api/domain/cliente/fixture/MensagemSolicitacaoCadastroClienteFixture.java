package com.cadastro.cliente.api.domain.cliente.fixture;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.mensagemsolicitacao.MensagemSolicitacaoCadastroCliente;

public class MensagemSolicitacaoCadastroClienteFixture {

    private Long idSolicitacao;
    private String cpfCnpjCliente;
    private String nomeCliente;
    private String numeroRGCliente;
    private String dataCriacao;
    private Double latitude;
    private Double longitude;
    private String nomeImagemRg;
    private String nomeImagemCpfCnpj;

    private MensagemSolicitacaoCadastroClienteFixture(){

        this.nomeCliente = "Pedro Teste";
        this.cpfCnpjCliente = "11752677687";
        this.numeroRGCliente = "16556789";
        this.dataCriacao = "19/04/2020";
        this.idSolicitacao = 1L;
        this.latitude = -3.45;
        this.longitude = -6.79;
        this.nomeImagemCpfCnpj = "CPF_CNPJ_Pedro_Fortini";
        this.nomeImagemRg = "RG_Pedro_Fortini";
    }

    public static MensagemSolicitacaoCadastroClienteFixture umMensagemSolicitacaoCadastroClienteFixture(){

        return new MensagemSolicitacaoCadastroClienteFixture();
    }

    public MensagemSolicitacaoCadastroCliente build(){

        MensagemSolicitacaoCadastroCliente mensagem = new MensagemSolicitacaoCadastroCliente();

        mensagem.setCpfCnpjCliente(this.cpfCnpjCliente);
        mensagem.setNomeCliente(this.nomeCliente);
        mensagem.setIdSolicitacao(this.idSolicitacao);
        mensagem.setLatitude(this.latitude);
        mensagem.setLongitude(this.longitude);
        mensagem.setNumeroRGCliente(this.numeroRGCliente);
        mensagem.setNomeImagemCpfCnpj(this.nomeImagemCpfCnpj);
        mensagem.setNomeImagemRg(this.nomeImagemRg);
        mensagem.setDataCriacao(this.dataCriacao);

        return mensagem;
    }
}
