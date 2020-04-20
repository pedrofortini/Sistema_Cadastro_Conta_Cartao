package com.analise.comunicador.module.infrastructure.persistencia;

public enum Comunicacoes {

    ANALISE_DOCUMENTOS_SUCESSO("A analise de seus documentos foi concluida com sucesso!"),
    ANALISE_DOCUMENTOS_FALHA("Ocorreram problemas na analise de seus documentos e seu pedido de cadastro de conta cartao foi rejeitado."),
    ANALISE_FRAUDE_SUCESSO("A analise de seus fraude de sua requisiçao de cadastro de conta corrente foi concluida com sucesso!"),
    ANALISE_FRAUDE_FALHA("Ocorreram problemas na analise de fraude e seu pedido de cadastro de conta cartao foi rejeitado."),
    ANALISE_CREDITO_SUCESSO("A analise de seus credito de sua requisiçao de cadastro de conta corrente foi concluida com sucesso, sua conta cartao ja foi criada!"),
    ANALISE_CREDITO_FALHA("Ocorreram problemas na analise de credito e seu pedido de cadastro de conta cartao foi rejeitado.")
    ;

    Comunicacoes(String mensagem){
        this.mensagem = mensagem;
    }

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }
}
