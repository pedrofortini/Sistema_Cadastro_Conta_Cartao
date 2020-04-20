package com.analise.fraude.module.domain.mensagemanalisecredito;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MensagemAnaliseCreditoCliente implements Serializable {

    private static final long serialVersionUID = -763993114170358524L;

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
}
