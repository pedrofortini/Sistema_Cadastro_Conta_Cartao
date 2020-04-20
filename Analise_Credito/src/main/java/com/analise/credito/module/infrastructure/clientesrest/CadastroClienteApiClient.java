package com.analise.credito.module.infrastructure.clientesrest;

import com.analise.credito.module.application.MessageConstants;
import com.analise.credito.module.domain.mensagemanalisecredito.StatusSolicitacao;
import com.analise.credito.module.application.factories.DependencyFactory;
import com.analise.credito.module.domain.mensagemanalisecredito.StatusContaCartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CadastroClienteApiClient {

    private DependencyFactory dependencyFactory;
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(CadastroClienteApiClient.class);

    private final int CONNECTION_TIMEOUT = 1000;

    public CadastroClienteApiClient(DependencyFactory dependencyFactory){

        this.dependencyFactory = dependencyFactory;
        this.restTemplate = new RestTemplate();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
        requestFactory.setReadTimeout(CONNECTION_TIMEOUT);

        restTemplate.setRequestFactory(requestFactory);
    }

    public ResponseEntity requestAtualizaStatusSolicitacao(Long idSolicitacao, StatusSolicitacao status){

        try{

            String url = this.dependencyFactory.getUrlServicoCadastroCliente() + "/solicitacoes";

            HttpHeaders headers = new HttpHeaders();
            headers.add("id", idSolicitacao.toString());
            headers.add("status", status.name());

            ResponseEntity<Void> response = this.restTemplate.exchange(url,
                    HttpMethod.PATCH, new HttpEntity(headers), Void.class);

            return response;
        }
        catch (HttpClientErrorException e){

            this.logger.error(String.format(
                    MessageConstants.MENSAGEM_ERRO_REQUEST_ATUALIZAR_STATUS_SOLICITACAO, idSolicitacao));
        }
        return null;
    }

    public ResponseEntity requestAtualizaStatusCadastroContaCartao(String cpfCnpj,
                                                                   StatusContaCartao status,
                                                                   String contaCartao){

        try{

            String url = this.dependencyFactory.getUrlServicoCadastroCliente() + "/solicitacoes";

            HttpHeaders headers = new HttpHeaders();
            headers.add("cpf_cnpj", cpfCnpj);
            headers.add("status", status.name());
            headers.add("conta_cartao", contaCartao);

            ResponseEntity<Void> response = this.restTemplate.exchange(url,
                    HttpMethod.PATCH, new HttpEntity(headers), Void.class);

            return response;
        }
        catch (HttpClientErrorException e){

            this.logger.error(String.format(
                    MessageConstants.MENSAGEM_ERRO_REQUEST_ATUALIZAR_STATUS_CADASTRO_CONTA_CARTAO, cpfCnpj));
        }
        return null;
    }
}
