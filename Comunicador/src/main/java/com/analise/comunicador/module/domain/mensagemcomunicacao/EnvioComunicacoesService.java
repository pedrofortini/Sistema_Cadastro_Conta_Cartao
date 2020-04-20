package com.analise.comunicador.module.domain.mensagemcomunicacao;

import com.analise.comunicador.module.application.ServiceConstants;
import com.analise.comunicador.module.infrastructure.mensageria.KafkaService;
import com.analise.comunicador.module.infrastructure.persistencia.DynamoDBService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Service;

@Service
public class EnvioComunicacoesService {

    private KafkaService kafkaService;
    private DynamoDBService dynamoDBService;

    public EnvioComunicacoesService(KafkaService kafkaService,
                                    DynamoDBService dynamoDBService){

        this.kafkaService = kafkaService;
        this.dynamoDBService = dynamoDBService;
    }


    public void processaMensagensTopicoAnaliseDocumentos(){

        final Consumer<Long, MensagemComunicacao> consumidor =
                this.kafkaService.criaConsumidorTopicoComunicacoes();

        int tentativas_count = 0;

        while (true) {

            final ConsumerRecords<Long, MensagemComunicacao> mensagensConsumidor =
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

    private void processaMensagem(MensagemComunicacao mensagem) {

        String mensagemComunicacao = this.dynamoDBService.
                buscaMensagemComunicacaoCliente(mensagem.getIdComunicacao(), mensagem.isSucessoAnalise());

        System.out.println(mensagemComunicacao);
    }
}
