package com.analise.credito.module.domain.mensagemanalisecredito;

import com.analise.credito.module.application.ServiceConstants;
import com.analise.credito.module.domain.mensagemcomunicacao.MensagemComunicacao;
import com.analise.credito.module.domain.mensagemcomunicacao.MensagemComunicacaoConverter;
import com.analise.credito.module.domain.mensagemcomunicacao.TipoComunicacao;
import com.analise.credito.module.infrastructure.clientesrest.CadastroClienteApiClient;
import com.analise.credito.module.infrastructure.mensageria.KafkaService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class AnaliseCreditoService {

    private KafkaService kafkaService;
    private MensagemComunicacaoConverter mensagemComunicacaoConverter;
    private CadastroClienteApiClient cadastroClienteApiClient;

    public AnaliseCreditoService(KafkaService kafkaService,
                                 MensagemComunicacaoConverter mensagemComunicacaoConverter,
                                 CadastroClienteApiClient cadastroClienteApiClient){

        this.kafkaService = kafkaService;
        this.mensagemComunicacaoConverter = mensagemComunicacaoConverter;
        this.cadastroClienteApiClient = cadastroClienteApiClient;
    }


    public void processaMensagensTopicoAnaliseCredito(){

        final Consumer<Long, MensagemAnaliseCreditoCliente> consumidor =
                this.kafkaService.criaConsumidorTopicoAnaliseCredito();

        int tentativas_count = 0;

        while (true) {

            final ConsumerRecords<Long, MensagemAnaliseCreditoCliente> mensagensConsumidor =
                    consumidor.poll(ServiceConstants.TAMANHO_POOL_TOPICO_ANALISE_DOCUMENTOS);

            if (mensagensConsumidor.count() == 0) {

                tentativas_count++;
                if (tentativas_count > ServiceConstants.NUM_MAXIMO_TENTATIVAS_TOPICO_ANALISE_DOCUMENTOS) break;
                else continue;
            }

            mensagensConsumidor.forEach(mensagem -> processaMensagem(mensagem.value()));
            consumidor.commitAsync();
        }
        consumidor.close();
    }

    private void processaMensagem(MensagemAnaliseCreditoCliente mensagem) {

        boolean resultadoAnaliseCredito = sucessoAnaliseCredito();

        if(resultadoAnaliseCredito){

            ResponseEntity response = this.cadastroClienteApiClient.requestAtualizaStatusSolicitacao(
                    mensagem.getIdSolicitacao(), StatusSolicitacao.APROVADA);

            ResponseEntity responseContaCartao = this.cadastroClienteApiClient.
                    requestAtualizaStatusCadastroContaCartao(
                            mensagem.getCpfCnpjCliente(), StatusContaCartao.CRIADA,
                            criaContaCartaoCliente().toString());

            if(validaSucessoRequest(response) && validaSucessoRequest(responseContaCartao)){

                enviaEmailComunicacao(mensagem, true);
            }
        }
        else{

            ResponseEntity response = this.cadastroClienteApiClient.requestAtualizaStatusSolicitacao(
                    mensagem.getIdSolicitacao(), StatusSolicitacao.REPROVADA);

            ResponseEntity responseContaCartao = this.cadastroClienteApiClient.requestAtualizaStatusCadastroContaCartao(
                    mensagem.getCpfCnpjCliente(), StatusContaCartao.REJEITADA, null);

            if(validaSucessoRequest(response) && validaSucessoRequest(responseContaCartao)){

                enviaEmailComunicacao(mensagem, false);
            }
        }

    }

    private boolean validaSucessoRequest(ResponseEntity response){

        return Objects.nonNull(response) && response.getStatusCode().is2xxSuccessful();
    }

    private void enviaEmailComunicacao(MensagemAnaliseCreditoCliente mensagem, boolean sucesso){

        MensagemComunicacao mensagemComunicacaoEmail = this.mensagemComunicacaoConverter.converte(mensagem,
                sucesso, TipoComunicacao.EMAIL);
        this.kafkaService.enviaMensagemComunicacao(mensagemComunicacaoEmail);
    }

    private boolean sucessoAnaliseCredito(){

        Random random = new Random();
        return random.nextBoolean();
    }

    private Long criaContaCartaoCliente(){

        Random random = new Random();
        return random.nextLong();
    }
}
