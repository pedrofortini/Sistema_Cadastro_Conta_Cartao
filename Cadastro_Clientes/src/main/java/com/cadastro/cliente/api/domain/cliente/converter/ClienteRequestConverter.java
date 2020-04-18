package com.cadastro.cliente.api.domain.cliente.converter;

import com.cadastro.cliente.api.application.MessageConstants;
import com.cadastro.cliente.api.application.exception.UnprocessableEntityException;
import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.cliente.ClienteService;
import com.cadastro.cliente.api.domain.cliente.StatusContaCartao;
import io.swagger.model.ClienteRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRequestConverter {

    private ClienteService clienteService;

    public ClienteRequestConverter(ClienteService clienteService){

        this.clienteService = clienteService;
    }

    public Cliente converte(ClienteRequest request){

        Cliente cliente = new Cliente();

        Optional<Cliente> optionalClienteNoBanco = clienteService.buscaPorCpfCnpj(request.getCpfCnpj());

        if(optionalClienteNoBanco.isPresent()){

            Cliente clienteNoBanco = optionalClienteNoBanco.get();

            if(StatusContaCartao.CRIADA.equals(clienteNoBanco.getStatusContaCartao())
                    || StatusContaCartao.ANALISE_PENDENTE.equals(clienteNoBanco.getStatusContaCartao())) {

                throw new UnprocessableEntityException(
                        String.format(MessageConstants.MENSAGEM_CADASTRO_CLIENTE_EM_ANALISE_OU_APROVADA,
                                request.getCpfCnpj()));
            }
        }

        cliente.setCpfCnpj(request.getCpfCnpj());
        cliente.setNome(request.getNome());
        cliente.setCidade(request.getCidade());
        cliente.setEstado(request.getEstado());
        cliente.setPais(request.getPais());
        cliente.setNumeroRG(request.getNumeroRg());
        cliente.setNomeImagemCpfCnpj(request.getNomeImagemCpfCnpj());
        cliente.setNomeImagemRg(request.getNomeImagemRg());
        cliente.setLatitude(request.getLatitude());
        cliente.setLongitude(request.getLongitude());
        cliente.setStatusContaCartao(StatusContaCartao.ANALISE_PENDENTE);

        return cliente;
    }
}
