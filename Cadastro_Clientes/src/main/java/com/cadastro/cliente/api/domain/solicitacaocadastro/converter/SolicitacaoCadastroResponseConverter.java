package com.cadastro.cliente.api.domain.solicitacaocadastro.converter;

import com.cadastro.cliente.api.application.util.DateUtil;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import io.swagger.model.SolicitacaoCadastroResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolicitacaoCadastroResponseConverter {

    public List<SolicitacaoCadastroResponse> converteListaSolicitacoes(List<Solicitacao> solicitacoes){

        return solicitacoes.stream().map(s -> converteResponse(s)).collect(Collectors.toList());
    }

    private SolicitacaoCadastroResponse converteResponse(Solicitacao solicitacao){

        SolicitacaoCadastroResponse response = new SolicitacaoCadastroResponse();

        response.setCpfCnpjCliente(solicitacao.getCliente().getCpfCnpj());
        response.setDataCriacao(DateUtil.dateToString(solicitacao.getDataCriacao()));
        response.setId(solicitacao.getId());
        response.setLatitude(solicitacao.getLatitude());
        response.setLongitude(solicitacao.getLongitude());
        response.setNomeImagemCpfCnpj(solicitacao.getNomeImagemCpfCnpj());
        response.setNomeImagemRg(solicitacao.getNomeImagemRg());
        response.setStatusSolicitacao(solicitacao.getStatus().name());

        return response;
    }
}
