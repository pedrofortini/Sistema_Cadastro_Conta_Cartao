package com.cadastro.cliente.api.domain.cliente.fixture;

import io.swagger.model.ClienteRequest;

public class ClienteRequestFixture {

    private String nome;
    private String cpfCnpj;
    private String numeroRg;
    private String cidade;
    private String estado;
    private String pais;
    private Double latitude;
    private Double longitude;
    private String nomeImagemRg;
    private String nomeImagemCpfCnpj;

    private ClienteRequestFixture(){

        this.nome = "Pedro Teste";
        this.cpfCnpj = "11752677687";
        this.numeroRg = "16556789";
        this.cidade = "Belo Horizonte";
        this.estado = "Minas Gerais";
        this.pais = "Brasil";
        this.latitude = -3.45;
        this.longitude = -6.79;
        this.nomeImagemCpfCnpj = "CPF_CNPJ_Pedro_Fortini";
        this.nomeImagemRg = "RG_Pedro_Fortini";
    }

    public static ClienteRequestFixture umClienteRequestFixture(){

        return new ClienteRequestFixture();
    }

    public ClienteRequest build(){

        ClienteRequest request = new ClienteRequest();

        request.setCpfCnpj(this.cpfCnpj);
        request.setNome(this.nome);
        request.setCidade(this.cidade);
        request.setEstado(this.estado);
        request.setPais(this.pais);
        request.setLatitude(this.latitude);
        request.setLongitude(this.longitude);
        request.setNumeroRg(this.numeroRg);
        request.setNomeImagemCpfCnpj(this.nomeImagemCpfCnpj);
        request.setNomeImagemRg(this.nomeImagemRg);

        return request;
    }
}
