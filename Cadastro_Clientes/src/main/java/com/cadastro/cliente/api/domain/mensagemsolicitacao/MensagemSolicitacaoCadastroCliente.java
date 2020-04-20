package com.cadastro.cliente.api.domain.mensagemsolicitacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MensagemSolicitacaoCadastroCliente implements Serializable {

    private static final long serialVersionUID = -4397640373592613168L;

    @JsonProperty("id_solicitacao")
    private Long idSolicitacao;

    @JsonProperty("cpf_cnpj_cliente")
    private String cpfCnpjCliente;

    @JsonProperty("nome_cliente")
    private String nomeCliente;

    @JsonProperty("numero_rg_cliente")
    private String numeroRGCliente;

    @JsonProperty("data_criacao_solicitacao")
    private String dataCriacao;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("nome_imagem_rg")
    private String nomeImagemRg;

    @JsonProperty("nome_imagem_cpf_cnpj")
    private String nomeImagemCpfCnpj;
}
