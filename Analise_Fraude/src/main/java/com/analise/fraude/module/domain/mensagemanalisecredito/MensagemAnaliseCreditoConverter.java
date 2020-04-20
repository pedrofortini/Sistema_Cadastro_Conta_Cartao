package com.analise.fraude.module.domain.mensagemanalisecredito;

import com.analise.fraude.module.domain.mensagemanalisecredito.MensagemAnaliseCreditoCliente;
import com.analise.fraude.module.domain.mensagemanalisefraude.MensagemAnaliseFraude;
import org.springframework.stereotype.Component;

@Component
public class MensagemAnaliseCreditoConverter {

    public MensagemAnaliseCreditoCliente converte(MensagemAnaliseFraude mensagem) {

        MensagemAnaliseCreditoCliente mensagemAnaliseCredito = new MensagemAnaliseCreditoCliente();

        mensagemAnaliseCredito.setCpfCnpjCliente(mensagem.getCpfCnpjCliente());
        mensagemAnaliseCredito.setDataCriacao(mensagem.getDataCriacao());
        mensagemAnaliseCredito.setIdSolicitacao(mensagem.getIdSolicitacao());
        mensagemAnaliseCredito.setNomeCliente(mensagem.getNomeCliente());
        mensagemAnaliseCredito.setNumeroRGCliente(mensagem.getNumeroRGCliente());

        return mensagemAnaliseCredito;
    }
}
