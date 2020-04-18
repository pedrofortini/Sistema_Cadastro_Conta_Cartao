package com.cadastro.cliente.api.domain.cliente.converter;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import io.swagger.model.ClienteResponse;
import org.springframework.stereotype.Component;

@Component
public class ClienteResponseConverter {

    public ClienteResponse converte(Cliente cliente){

        ClienteResponse response = new ClienteResponse();

        response.setCpfCnpj(cliente.getCpfCnpj());
        response.setNumeroRg(cliente.getNumeroRG());
        response.setCidade(cliente.getCidade());
        response.setEstado(cliente.getEstado());
        response.setPais(cliente.getPais());
        response.setNome(cliente.getNome());
        response.setStatusCriacaoConta(cliente.getStatusContaCartao().name());
        response.setContaCartao(cliente.getContaCartao());

        return response;
    }
}
