package com.analise.fraude.module.domain.solicitacaocadastro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SolicitacaoCadastroResponse implements Serializable {

    private static final long serialVersionUID = 4930960424123598001L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("data_criacao")
    private String dataCriacao;

    @JsonProperty("cpf_cnpj_cliente")
    private String cpfCnpjCliente;

    @JsonProperty("status_solicitacao")
    private String statusSolicitacao;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("nome_imagem_rg")
    private String nomeImagemRg;

    @JsonProperty("nome_imagem_cpf_cnpj")
    private String nomeImagemCpfCnpj;
}
