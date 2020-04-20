package com.analise.fraude.module.infrastructure.clientesrest;

import com.analise.fraude.module.application.MessageConstants;
import com.analise.fraude.module.application.factories.DependencyFactory;
import com.analise.fraude.module.domain.mensagemanalisefraude.StatusContaCartao;
import com.analise.fraude.module.domain.mensagemanalisefraude.StatusSolicitacao;
import com.analise.fraude.module.domain.solicitacaocadastro.SolicitacaoCadastroResponse;
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

import java.util.Arrays;
import java.util.List;

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

    public ResponseEntity requestAtualizaStatusCadastroContaCartao(String cpfCnpj, StatusContaCartao status){

        try{

            String url = this.dependencyFactory.getUrlServicoCadastroCliente() + "/clientes";

            HttpHeaders headers = new HttpHeaders();
            headers.add("cpf_cnpj", cpfCnpj);
            headers.add("status", status.name());

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

    public List<SolicitacaoCadastroResponse> requestObtemListaSolicitacoesClienteNoIntervalo(String cpfCnpj,
                                                                                             String dataInicial,
                                                                                             String dataFinal) {

        try{

            String url = this.dependencyFactory.getUrlServicoCadastroCliente() + "/solicitacoes";

            HttpHeaders headers = new HttpHeaders();
            headers.add("cpf_cnpj", cpfCnpj);
            headers.add("data_inicial", dataInicial);
            headers.add("data_final", dataFinal);

            ResponseEntity<SolicitacaoCadastroResponse[]> response = this.restTemplate.exchange(url,
                    HttpMethod.GET, new HttpEntity(headers), SolicitacaoCadastroResponse[].class);

            return Arrays.asList(response.getBody());
        }
        catch (HttpClientErrorException e){

            this.logger.error(String.format(
                    MessageConstants.MENSAGEM_ERRO_REQUEST_OBTEM_SOLICITACOES_CLIENTE, dataInicial, dataFinal, cpfCnpj));
        }
        return null;
    }
}
