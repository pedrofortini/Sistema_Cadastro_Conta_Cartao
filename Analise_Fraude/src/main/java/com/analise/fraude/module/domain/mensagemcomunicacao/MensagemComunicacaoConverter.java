package com.analise.fraude.module.domain.mensagemcomunicacao;

import com.analise.fraude.module.domain.mensagemanalisecredito.MensagemAnaliseCreditoCliente;
import com.analise.fraude.module.domain.mensagemanalisefraude.MensagemAnaliseFraude;
import org.springframework.stereotype.Component;

@Component
public class MensagemComunicacaoConverter {

    public MensagemComunicacao converte(MensagemAnaliseFraude mensagem,
                                        boolean sucessoAnalise,
                                        TipoComunicacao tipoComunicacao) {

        MensagemComunicacao mensagemComunicacao = new MensagemComunicacao();

        mensagemComunicacao.setCpfCnpjCliente(mensagem.getCpfCnpjCliente());
        mensagemComunicacao.setNomeCliente(mensagem.getNomeCliente());
        mensagemComunicacao.setNumeroRGCliente(mensagem.getNumeroRGCliente());
        mensagemComunicacao.setSucessoAnalise(sucessoAnalise);
        mensagemComunicacao.setTipoComunicacao(tipoComunicacao.name());

        return mensagemComunicacao;
    }
}
