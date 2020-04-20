package com.analise.fraude.module.application;

public interface MessageConstants {

    String MENSAGEM_ERRO_REQUEST_ATUALIZAR_STATUS_SOLICITACAO = "Ocorreu um erro ao tentar atualizar o status da solicitaçao de id: %s";
    String MENSAGEM_ERRO_REQUEST_ATUALIZAR_STATUS_CADASTRO_CONTA_CARTAO = "Ocorreu um erro ao tentar atualizar o status do cadastro da conta cartao para o cliente de CPF/CNPJ %s";
    String MENSAGEM_ERRO_REQUEST_OBTEM_SOLICITACOES_CLIENTE = "Ocorreu um erro ao tentar os dados das solicitacoes no periodo de %s a %s, para o cliente de CPF/CNPJ %s";
    String MENSAGEM_DATA_FORMATO_INVALIDO = "Erro ao processar data %s";
}
