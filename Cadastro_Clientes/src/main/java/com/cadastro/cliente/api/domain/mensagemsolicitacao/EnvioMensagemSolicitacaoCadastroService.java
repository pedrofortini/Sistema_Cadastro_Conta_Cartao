package com.cadastro.cliente.api.domain.mensagemsolicitacao;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.mensagemsolicitacao.converter.MensagemSolicitacaoCadastroClienteConverter;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.infrastructure.mensageria.KafkaService;
import org.springframework.stereotype.Service;

@Service
public class EnvioMensagemSolicitacaoCadastroService {

    private KafkaService kafkaService;
    private MensagemSolicitacaoCadastroClienteConverter converter;

    public EnvioMensagemSolicitacaoCadastroService(KafkaService kafkaService,
                                                   MensagemSolicitacaoCadastroClienteConverter converter){

        this.kafkaService = kafkaService;
        this.converter = converter;
    }

    public void enviaMensagemSolicitacaoCadastro(Cliente cliente, Solicitacao solicitacao){

        MensagemSolicitacaoCadastroCliente  mensagem = converter.converte(cliente, solicitacao);
        kafkaService.enviaMensagem(mensagem);
    }
}
