package com.analise.fraude.module.domain.mensagemanalisefraude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MensagemAnaliseFraude implements Serializable {

    private static final long serialVersionUID = -8299582665985034096L;

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
}
