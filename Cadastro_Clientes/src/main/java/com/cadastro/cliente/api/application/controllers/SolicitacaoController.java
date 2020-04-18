package com.cadastro.cliente.api.application.controllers;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.ClienteService;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.SolicitacaoCadastroService;
import com.cadastro.cliente.api.domain.solicitacaocadastro.converter.SolicitacaoCadastroResponseConverter;
import io.swagger.api.SolicitacoesApi;
import io.swagger.model.SolicitacaoCadastroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SolicitacaoController implements SolicitacoesApi {

    private SolicitacaoCadastroService solicitacaoCadastroService;
    private SolicitacaoCadastroResponseConverter converter;
    private ClienteService clienteService;

    public SolicitacaoController( SolicitacaoCadastroService solicitacaoCadastroService,
                                  ClienteService clienteService,
                                  SolicitacaoCadastroResponseConverter converter){

        this.solicitacaoCadastroService = solicitacaoCadastroService;
        this.clienteService = clienteService;
        this.converter = converter;
    }

    @Override
    public ResponseEntity<Void> atualizaStatusSolicitacaoCliente(@RequestHeader(value="id", required=true) Long id,
                                                                 @RequestHeader(value="status", required=false) String status) {

        solicitacaoCadastroService.atualizacaoParcialSolicitacaoCliente(id, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<SolicitacaoCadastroResponse>> obtemSolicitacoesClienteNoPeriodo(
            @RequestHeader(value="cpf_cnpj", required=true) String cpfCnpj,
            @RequestHeader(value="data_inicial", required=true) String dataInicial,
            @RequestHeader(value="data_final", required=true) String dataFinal) {

        Cliente cliente = clienteService.buscaDadosCliente(cpfCnpj);
        List<Solicitacao> solicitacoes = solicitacaoCadastroService.buscaSolicitacoesDoClienteNoIntervalo(cliente,
                dataInicial, dataFinal);
        List<SolicitacaoCadastroResponse> solicitacoesResponse = converter.converteListaSolicitacoes(solicitacoes);

        return ResponseEntity.ok(solicitacoesResponse);
    }
}
