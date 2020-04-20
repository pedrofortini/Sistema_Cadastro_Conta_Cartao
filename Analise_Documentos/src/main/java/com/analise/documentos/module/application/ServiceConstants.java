package com.analise.documentos.module.application;

public interface ServiceConstants {

    String NOME_TOPICO_ANALISE_DOCUMENTOS = "cadastro-conta-cartao-analise-documentos-topic";
    String NOME_TOPICO_COMUNICACAO = "cadastro-conta-cartao-comunicacoes-topic";
    String NOME_TOPICO_ANALISE_FRAUDE = "cadastro-conta-cartao-analise-fraude-topic";

    String ID_COMUNICACAO_ANALISE_DOCUMENTOS = "ANALISE_DOCUMENTOS";

    Integer TAMANHO_POOL_TOPICO_ANALISE_DOCUMENTOS = 1000;
    Integer NUM_MAXIMO_TENTATIVAS_TOPICO_ANALISE_DOCUMENTOS = 100;
}
