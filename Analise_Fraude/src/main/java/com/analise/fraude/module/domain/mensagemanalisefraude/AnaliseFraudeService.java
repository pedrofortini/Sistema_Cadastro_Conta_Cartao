package com.analise.fraude.module.domain.mensagemanalisefraude;

import com.analise.fraude.module.application.ServiceConstants;
import com.analise.fraude.module.application.util.DateUtil;
import com.analise.fraude.module.domain.geolocalizacao.GeoLocalizacaoService;
import com.analise.fraude.module.domain.mensagemanalisecredito.MensagemAnaliseCreditoCliente;
import com.analise.fraude.module.domain.mensagemanalisecredito.MensagemAnaliseCreditoConverter;
import com.analise.fraude.module.domain.mensagemcomunicacao.MensagemComunicacao;
import com.analise.fraude.module.domain.mensagemcomunicacao.MensagemComunicacaoConverter;
import com.analise.fraude.module.domain.mensagemcomunicacao.TipoComunicacao;
import com.analise.fraude.module.domain.solicitacaocadastro.SolicitacaoCadastroResponse;
import com.analise.fraude.module.infrastructure.clientesrest.CadastroClienteApiClient;
import com.analise.fraude.module.infrastructure.mensageria.KafkaService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class AnaliseFraudeService {

    private KafkaService kafkaService;
    private MensagemComunicacaoConverter mensagemComunicacaoConverter;
    private MensagemAnaliseCreditoConverter mensagemAnaliseCreditoConverter;
    private GeoLocalizacaoService geoLocalizacaoService;
    private CadastroClienteApiClient cadastroClienteApiClient;

    public AnaliseFraudeService(KafkaService kafkaService,
                                MensagemComunicacaoConverter mensagemComunicacaoConverter,
                                MensagemAnaliseCreditoConverter mensagemAnaliseCreditoConverter,
                                CadastroClienteApiClient cadastroClienteApiClient,
                                GeoLocalizacaoService geoLocalizacaoService){

        this.kafkaService = kafkaService;
        this.mensagemComunicacaoConverter = mensagemComunicacaoConverter;
        this.mensagemAnaliseCreditoConverter = mensagemAnaliseCreditoConverter;
        this.cadastroClienteApiClient = cadastroClienteApiClient;
        this.geoLocalizacaoService = geoLocalizacaoService;
    }


    public void processaMensagensTopicoAnaliseFraude(){

        final Consumer<Long, MensagemAnaliseFraude> consumidor =
                this.kafkaService.criaConsumidorTopicoAnaliseFraude();

        int tentativas_count = 0;

        while (true) {

            final ConsumerRecords<Long, MensagemAnaliseFraude> mensagensConsumidor =
                    consumidor.poll(ServiceConstants.TAMANHO_POOL_TOPICO_ANALISE_FRAUDE);

            if (mensagensConsumidor.count() == 0) {

                tentativas_count++;
                if (tentativas_count > ServiceConstants.NUM_MAXIMO_TENTATIVAS_TOPICO_ANALISE_FRAUDE) break;
                else continue;
            }

            mensagensConsumidor.forEach(mensagem -> processaMensagem(mensagem.value()));
            consumidor.commitAsync();
        }
        consumidor.close();
    }

    private void processaMensagem(MensagemAnaliseFraude mensagem) {

        boolean resultadoAnaliseFraude = sucessoAnaliseFraudeSolicitacoesCliente(mensagem);

        if(resultadoAnaliseFraude){

            ResponseEntity response = this.cadastroClienteApiClient.requestAtualizaStatusSolicitacao(
                    mensagem.getIdSolicitacao(), StatusSolicitacao.EM_ANALISE_CREDITO);

            if(Objects.nonNull(response)){
                if(response.getStatusCode().is2xxSuccessful()){

                    enviaEmailComunicacao(mensagem, true);

                    MensagemAnaliseCreditoCliente mensagemAnaliseCreditoCliente =
                            mensagemAnaliseCreditoConverter.converte(mensagem);
                    this.kafkaService.enviaMensagemAnaliseCredito(mensagemAnaliseCreditoCliente);
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

    private void enviaEmailComunicacao(MensagemAnaliseFraude mensagem, boolean sucesso){

        MensagemComunicacao mensagemComunicacaoEmail = this.mensagemComunicacaoConverter.converte(mensagem,
                sucesso, TipoComunicacao.EMAIL);
        this.kafkaService.enviaMensagemComunicacao(mensagemComunicacaoEmail);
    }

    private boolean sucessoAnaliseFraudeSolicitacoesCliente(MensagemAnaliseFraude mensagem){

        Date dataInicial = DateUtil.stringToDate(mensagem.getDataCriacao());
        Date dataFinal = DateUtil.obtemDataSemanaAnterior(dataInicial);

        List<SolicitacaoCadastroResponse> solicitacoesCliente = this.cadastroClienteApiClient.
                requestObtemListaSolicitacoesClienteNoIntervalo(
                        mensagem.getCpfCnpjCliente(),
                        DateUtil.dateToString(dataInicial),
                        DateUtil.dateToString(dataFinal));

        Double mediaDistanciaSolicitacoes = this.geoLocalizacaoService.obtemDistanciaMediaEntreSolicitacoes(
                mensagem.getLatitude(), mensagem.getLongitude(), solicitacoesCliente);

        return mediaDistanciaSolicitacoes < ServiceConstants.DISTANCIA_MEDIA_MAXIMA_ENTRE_SOLICITACOES;
    }
}
