package com.analise.fraude.module.application;

public interface ServiceConstants {

    String NOME_TOPICO_ANALISE_CREDITO = "cadastro-conta-cartao-analise-credito-topic";
    String NOME_TOPICO_COMUNICACAO = "cadastro-conta-cartao-comunicacoes-topic";
    String NOME_TOPICO_ANALISE_FRAUDE = "cadastro-conta-cartao-analise-fraude-topic";

    String ID_COMUNICACAO_ANALISE_FRAUDE = "ANALISE_FRAUDE";

    Integer TAMANHO_POOL_TOPICO_ANALISE_FRAUDE = 1000;
    Integer NUM_MAXIMO_TENTATIVAS_TOPICO_ANALISE_FRAUDE = 100;

    Integer DISTANCIA_MEDIA_MAXIMA_ENTRE_SOLICITACOES = 1000;
}
