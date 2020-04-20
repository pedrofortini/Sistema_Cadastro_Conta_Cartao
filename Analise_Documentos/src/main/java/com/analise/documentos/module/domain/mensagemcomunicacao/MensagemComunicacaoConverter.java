package com.analise.documentos.module.domain.mensagemcomunicacao;

import com.analise.documentos.module.domain.mensagemanalisedocumentos.MensagemAnaliseDocumentosCliente;
import org.springframework.stereotype.Component;

@Component
public class MensagemComunicacaoConverter {

    public MensagemComunicacao converte(MensagemAnaliseDocumentosCliente mensagem,
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
