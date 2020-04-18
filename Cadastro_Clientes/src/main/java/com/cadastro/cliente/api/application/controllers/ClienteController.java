package com.cadastro.cliente.api.application.controllers;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.ClienteService;
import com.cadastro.cliente.api.domain.cliente.converter.ClienteRequestConverter;
import com.cadastro.cliente.api.domain.cliente.converter.ClienteResponseConverter;
import io.swagger.api.ClientesApi;
import io.swagger.model.ClienteRequest;
import io.swagger.model.ClienteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClienteController implements ClientesApi {

    private ClienteRequestConverter clienteRequestConverter;
    private ClienteResponseConverter clienteResponseConverter;
    private ClienteService clienteService;

    public ClienteController(ClienteRequestConverter clienteRequestConverter,
                             ClienteResponseConverter clienteResponseConverter,
                             ClienteService clienteService) {

        this.clienteRequestConverter = clienteRequestConverter;
        this.clienteResponseConverter = clienteResponseConverter;
        this.clienteService = clienteService;
    }

    @Override
    public ResponseEntity<Void> atualizaStatusCliente(@RequestHeader(value="cpf_cnpj", required=true) String cpfCnpj,
                                                      @RequestHeader(value="status", required=false) String status,
                                                      @RequestHeader(value="conta_cartao", required=false) String contaCartao) {

        this.clienteService.atualizacaoParcialCliente(cpfCnpj, status, contaCartao);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ClienteResponse> obtemDadosCliente(@RequestHeader(value="cpf_cnpj", required=true) String cpfCnpj) {

        Cliente cliente = this.clienteService.buscaDadosCliente(cpfCnpj);
        return ResponseEntity.ok(this.clienteResponseConverter.converte(cliente));
    }

    @Override
    public ResponseEntity<Void> salvaCliente(@Valid @RequestBody ClienteRequest clienteRequest) {

        Cliente cliente = this.clienteRequestConverter.converte(clienteRequest);
        this.clienteService.salvaCliente(cliente);

        return ResponseEntity.ok().build();
    }
}
