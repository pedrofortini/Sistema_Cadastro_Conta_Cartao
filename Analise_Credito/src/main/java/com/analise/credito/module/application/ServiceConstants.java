package com.analise.credito.module.application;

public interface ServiceConstants {

    String NOME_TOPICO_ANALISE_CREDITO = "cadastro-conta-cartao-analise-credito-topic";
    String NOME_TOPICO_COMUNICACAO = "cadastro-conta-cartao-comunicacoes-topic";

    String ID_COMUNICACAO_ANALISE_CREDITO = "ANALISE_CREDITO";

    Integer TAMANHO_POOL_TOPICO_ANALISE_DOCUMENTOS = 1000;
    Integer NUM_MAXIMO_TENTATIVAS_TOPICO_ANALISE_DOCUMENTOS = 100;
}
