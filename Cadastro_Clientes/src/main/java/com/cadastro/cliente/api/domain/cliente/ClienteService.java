package com.cadastro.cliente.api.domain.cliente;

import com.cadastro.cliente.api.application.MessageConstants;
import com.cadastro.cliente.api.application.exception.PersistenceException;
import com.cadastro.cliente.api.application.exception.RecursoNaoEncontradoException;
import com.cadastro.cliente.api.domain.mensagemsolicitacao.EnvioMensagemSolicitacaoCadastroService;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import com.cadastro.cliente.api.domain.solicitacaocadastro.SolicitacaoCadastroService;
import com.cadastro.cliente.api.infrastructure.persistencia.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private SolicitacaoCadastroService solicitacaoCadastroService;
    private EnvioMensagemSolicitacaoCadastroService envioMensagemService;

    Logger logger = LoggerFactory.getLogger(ClienteService.class);

    public ClienteService(ClienteRepository clienteRepository,
                          SolicitacaoCadastroService solicitacaoCadastroService,
                          EnvioMensagemSolicitacaoCadastroService envioMensagemService){

        this.clienteRepository = clienteRepository;
        this.solicitacaoCadastroService = solicitacaoCadastroService;
        this.envioMensagemService = envioMensagemService;
    }

    public Optional<Cliente> buscaPorCpfCnpj(String cpfCnpj){

        return clienteRepository.findById(cpfCnpj);
    }

    public Cliente buscaDadosCliente(String cpfCnpj){

        Optional<Cliente> clienteNoBanco = this.buscaPorCpfCnpj(cpfCnpj);

        if(!clienteNoBanco.isPresent()) {

            throw new RecursoNaoEncontradoException(String.format(
                    MessageConstants.MENSAGEM_CLIENTE_NAO_ENCONTRADO, cpfCnpj));
        }

        return clienteNoBanco.get();
    }

    public Cliente salvaCliente(Cliente cliente) {

        try {

            Cliente clienteSalvo = this.clienteRepository.save(cliente);
            Solicitacao solicitacaoSalva = solicitacaoCadastroService.registraSolicitacaoCadastroCliente(cliente);

            this.envioMensagemService.enviaMensagemSolicitacaoCadastro(clienteSalvo, solicitacaoSalva);

            return clienteSalvo;
        }
        catch (Exception e){

            logger.error(String.format(MessageConstants.MENSAGEM_ERRO_AO_PERSISTIR_DADO_CLIENTE, cliente.getCpfCnpj()), e);
            throw new PersistenceException(String.format(MessageConstants.MENSAGEM_ERRO_AO_PERSISTIR_DADO_CLIENTE,
                    cliente.getCpfCnpj()));
        }
    }

    public Cliente atualizacaoParcialCliente(String cpfCnpj, String status, String contaCartao){

        Cliente clienteNoBanco = this.buscaDadosCliente(cpfCnpj);

        if(!StringUtils.isEmpty(status)) clienteNoBanco.setStatusContaCartao(StatusContaCartao.valueOf(status));
        if(!StringUtils.isEmpty(contaCartao)) clienteNoBanco.setContaCartao(contaCartao);

        Cliente clienteSalvo = this.clienteRepository.save(clienteNoBanco);
        return clienteSalvo;
    }
}
