package com.cadastro.cliente.api.domain.cliente.fixture;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;

public class ClienteFixture {

    private String cpfCnpj;
    private String nome;
    private String numeroRG;
    private String cidade;
    private String estado;
    private String pais;
    private StatusContaCartao statusContaCartao;
    private String contaCartao;
    private String nomeImagemRg;
    private String nomeImagemCpfCnpj;
    private Double latitude;
    private Double longitude;

    private ClienteFixture(){

        this.nome = "Pedro Teste";
        this.cpfCnpj = "11752677687";
        this.numeroRG = "16556789";
        this.cidade = "Belo Horizonte";
        this.estado = "Minas Gerais";
        this.pais = "Brasil";
        this.latitude = -3.45;
        this.longitude = -6.79;
        this.nomeImagemCpfCnpj = "CPF_CNPJ_Pedro_Fortini";
        this.nomeImagemRg = "RG_Pedro_Fortini";
        this.statusContaCartao = StatusContaCartao.ANALISE_PENDENTE;
        this.contaCartao = null;
    }

    public static ClienteFixture umClienteFixture(){

        return new ClienteFixture();
    }

    public Cliente build(){

        Cliente cliente = new Cliente();

        cliente.setCpfCnpj(this.cpfCnpj);
        cliente.setNome(this.nome);
        cliente.setCidade(this.cidade);
        cliente.setEstado(this.estado);
        cliente.setPais(this.pais);
        cliente.setLatitude(this.latitude);
        cliente.setLongitude(this.longitude);
        cliente.setNumeroRG(this.numeroRG);
        cliente.setNomeImagemCpfCnpj(this.nomeImagemCpfCnpj);
        cliente.setNomeImagemRg(this.nomeImagemRg);
        cliente.setStatusContaCartao(this.statusContaCartao);
        cliente.setContaCartao(this.contaCartao);

        return cliente;
    }

    public ClienteFixture comStatusContaCartaoCriada(){

        this.statusContaCartao = StatusContaCartao.CRIADA;
        return this;
    }

    public ClienteFixture comStatusContaCartaoRejeitada(){

        this.statusContaCartao = StatusContaCartao.REJEITADA;
        return this;
    }

}
