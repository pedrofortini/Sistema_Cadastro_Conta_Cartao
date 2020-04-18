package com.cadastro.cliente.api.application;

public interface MessageConstants {

    String MENSAGEM_CLIENTE_NAO_ENCONTRADO = "Nao foi possivel encontrar o cliente com CPF/CNPJ %s";
    String MENSAGEM_SOLICITACAO_CADASTRO_NAO_ENCONTRADA = "Nao foi possivel encontrar a solicitaçao de cadastro de identificador %s";
    String MENSAGEM_CADASTRO_CLIENTE_EM_ANALISE_OU_APROVADA = "O Cliente de CPF/CNPJ %s ja possui uma solicitaçao de cadastro em analise ou aprovada";
    String MENSAGEM_ERRO_AO_PERSISTIR_DADO_CLIENTE = "Erro ao persistir dados do Cliente de CPF/CNPJ %s";
    String MENSAGEM_ERRO_AO_PERSISTIR_SOLICITACAO_CADASTRO_CLIENTE = "Erro ao persistir dados da Solicitaçao de Cadastro do Cliente de CPF/CNPJ %s";
    String MENSAGEM_DATA_FORMATO_INVALIDO = "Erro ao processar data %s";

}
