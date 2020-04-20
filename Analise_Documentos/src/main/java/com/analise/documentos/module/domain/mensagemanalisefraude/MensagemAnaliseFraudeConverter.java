package com.analise.documentos.module.domain.mensagemanalisefraude;

import com.analise.documentos.module.domain.mensagemanalisedocumentos.MensagemAnaliseDocumentosCliente;
import org.springframework.stereotype.Component;

@Component
public class MensagemAnaliseFraudeConverter {

    public MensagemAnaliseFraude converte(MensagemAnaliseDocumentosCliente mensagem) {

        MensagemAnaliseFraude mensagemAnaliseFraude = new MensagemAnaliseFraude();

        mensagemAnaliseFraude.setCpfCnpjCliente(mensagem.getCpfCnpjCliente());
        mensagemAnaliseFraude.setDataCriacao(mensagem.getDataCriacao());
        mensagemAnaliseFraude.setIdSolicitacao(mensagem.getIdSolicitacao());
        mensagemAnaliseFraude.setLatitude(mensagem.getLatitude());
        mensagemAnaliseFraude.setLongitude(mensagem.getLongitude());
        mensagemAnaliseFraude.setNomeCliente(mensagem.getNomeCliente());
        mensagemAnaliseFraude.setNumeroRGCliente(mensagem.getNumeroRGCliente());

        return mensagemAnaliseFraude;
    }
}
