package com.analise.comunicador.module.infrastructure.persistencia;

import org.springframework.stereotype.Service;

@Service
public class DynamoDBService {

    public String buscaMensagemComunicacaoCliente(String idComunicacao, boolean sucesso){

        String sufixo = sucesso ? "_SUCESSO" : "_FALHA";
        return Comunicacoes.valueOf(idComunicacao.concat(sufixo)).getMensagem();
    }
}
