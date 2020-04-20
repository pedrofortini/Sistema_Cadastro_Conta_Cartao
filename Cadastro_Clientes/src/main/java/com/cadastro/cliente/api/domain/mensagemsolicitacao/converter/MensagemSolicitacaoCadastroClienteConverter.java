package com.cadastro.cliente.api.domain.mensagemsolicitacao.converter;

import com.cadastro.cliente.api.application.util.DateUtil;
import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.mensagemsolicitacao.MensagemSolicitacaoCadastroCliente;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import org.springframework.stereotype.Component;

@Component
public class MensagemSolicitacaoCadastroClienteConverter {

    public MensagemSolicitacaoCadastroCliente converte(Cliente cliente, Solicitacao solicitacao){

        MensagemSolicitacaoCadastroCliente mensagem =
                new MensagemSolicitacaoCadastroCliente();

        mensagem.setIdSolicitacao(solicitacao.getId());
        mensagem.setCpfCnpjCliente(cliente.getCpfCnpj());
        mensagem.setNumeroRGCliente(cliente.getNumeroRG());
        mensagem.setDataCriacao(DateUtil.dateToString(solicitacao.getDataCriacao()));
        mensagem.setLatitude(solicitacao.getLatitude());
        mensagem.setLongitude(solicitacao.getLongitude());
        mensagem.setNomeCliente(cliente.getNome());
        mensagem.setNomeImagemCpfCnpj(solicitacao.getNomeImagemCpfCnpj());
        mensagem.setNomeImagemRg(solicitacao.getNomeImagemRg());

        return mensagem;
    }
}
