package com.analise.fraude.module.domain.mensagemcomunicacao;

import com.analise.fraude.module.application.ServiceConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MensagemComunicacao implements Serializable {

    private static final long serialVersionUID = -8299582665985034096L;

    @JsonProperty("cpf_cnpj_cliente")
    private String cpfCnpjCliente;

    @JsonProperty("nome_cliente")
    private String nomeCliente;

    @JsonProperty("numero_rg_cliente")
    private String numeroRGCliente;

    @JsonProperty("tipo_comunicacao")
    private String tipoComunicacao;

    @JsonProperty("id_comunicacao")
    private String idComunicacao = ServiceConstants.ID_COMUNICACAO_ANALISE_FRAUDE;

    @JsonProperty("sucesso_analise")
    private boolean sucessoAnalise;
}

