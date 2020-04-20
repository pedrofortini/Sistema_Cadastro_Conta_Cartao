package com.analise.credito.module.domain.mensagemcomunicacao;

import com.analise.credito.module.domain.mensagemanalisecredito.MensagemAnaliseCreditoCliente;
import org.springframework.stereotype.Component;

@Component
public class MensagemComunicacaoConverter {

    public MensagemComunicacao converte(MensagemAnaliseCreditoCliente mensagem,
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
