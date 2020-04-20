package com.analise.documentos.module.domain.mensagemanalisedocumentos;

import com.analise.documentos.module.application.ServiceConstants;
import com.analise.documentos.module.domain.mensagemanalisefraude.MensagemAnaliseFraude;
import com.analise.documentos.module.domain.mensagemanalisefraude.MensagemAnaliseFraudeConverter;
import com.analise.documentos.module.domain.mensagemcomunicacao.MensagemComunicacao;
import com.analise.documentos.module.domain.mensagemcomunicacao.MensagemComunicacaoConverter;
import com.analise.documentos.module.domain.mensagemcomunicacao.TipoComunicacao;
import com.analise.documentos.module.infrastructure.clientesrest.CadastroClienteApiClient;
import com.analise.documentos.module.infrastructure.mensageria.KafkaService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class AnaliseDocumentosService {

    private KafkaService kafkaService;
    private MensagemComunicacaoConverter mensagemComunicacaoConverter;
    private MensagemAnaliseFraudeConverter mensagemAnaliseFraudeConverter;
    private CadastroClienteApiClient cadastroClienteApiClient;

    public AnaliseDocumentosService(KafkaService kafkaService,
                                    MensagemComunicacaoConverter mensagemComunicacaoConverter,
                                    MensagemAnaliseFraudeConverter mensagemAnaliseFraudeConverter,
                                    CadastroClienteApiClient cadastroClienteApiClient){

        this.kafkaService = kafkaService;
        this.mensagemComunicacaoConverter = mensagemComunicacaoConverter;
        this.mensagemAnaliseFraudeConverter = mensagemAnaliseFraudeConverter;
        this.cadastroClienteApiClient = cadastroClienteApiClient;
    }


    public void processaMensagensTopicoAnaliseDocumentos(){

        final Consumer<Long, MensagemAnaliseDocumentosCliente> consumidor =
                this.kafkaService.criaConsumidorTopicoAnaliseDocumentos();

        int tentativas_count = 0;

        while (true) {

            final ConsumerRecords<Long, MensagemAnaliseDocumentosCliente> mensagensConsumidor =
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

    private void processaMensagem(MensagemAnaliseDocumentosCliente mensagem) {

        boolean resultadoAnaliseDocumentos = sucessoAnaliseDocumentos();

        if(resultadoAnaliseDocumentos){

            ResponseEntity response = this.cadastroClienteApiClient.requestAtualizaStatusSolicitacao(
                    mensagem.getIdSolicitacao(), StatusSolicitacao.EM_ANALISE_FRAUDE);

            if(Objects.nonNull(response)){
                if(response.getStatusCode().is2xxSuccessful()){

                    enviaEmailComunicacao(mensagem, true);

                    MensagemAnaliseFraude mensagemAnaliseFraude = mensagemAnaliseFraudeConverter.converte(mensagem);
                    this.kafkaService.enviaMensagemAnaliseFraude(mensagemAnaliseFraude);
                }
            }
        }
        else{

            this.cadastroClienteApiClient.requestAtualizaStatusSolicitacao(
                    mensagem.getIdSolicitacao(), StatusSolicitacao.REPROVADA);

            this.cadastroClienteApiClient.requestAtualizaStatusCadastroContaCartao(
                    mensagem.getCpfCnpjCliente(), StatusContaCartao.REJEITADA);

            enviaEmailComunicacao(mensagem, false);
        }

    }

    private void enviaEmailComunicacao(MensagemAnaliseDocumentosCliente mensagem, boolean sucesso){

        MensagemComunicacao mensagemComunicacaoEmail = this.mensagemComunicacaoConverter.converte(mensagem,
                sucesso, TipoComunicacao.EMAIL);
        this.kafkaService.enviaMensagemComunicacao(mensagemComunicacaoEmail);
    }

    private boolean sucessoAnaliseDocumentos(){

        Random random = new Random();
        return random.nextBoolean();
    }
}
