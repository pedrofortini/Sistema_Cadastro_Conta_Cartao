package com.cadastro.cliente.api.domain.solicitacaocadastro;

import com.cadastro.cliente.api.application.MessageConstants;
import com.cadastro.cliente.api.application.exception.PersistenceException;
import com.cadastro.cliente.api.application.exception.RecursoNaoEncontradoException;
import com.cadastro.cliente.api.application.util.DateUtil;
import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.infrastructure.persistencia.SolicitacaoCadastroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoCadastroService {

    SolicitacaoCadastroRepository solicitacaoCadastroRepository;

    Logger logger = LoggerFactory.getLogger(SolicitacaoCadastroService.class);

    public SolicitacaoCadastroService(SolicitacaoCadastroRepository solicitacaoCadastroRepository){

        this.solicitacaoCadastroRepository = solicitacaoCadastroRepository;
    }

    public Solicitacao buscaSolicitacaoPorId(Long id){

        Optional<Solicitacao> optionalSolicitacao = this.solicitacaoCadastroRepository.findById(id);

        if(!optionalSolicitacao.isPresent()) {

            throw new RecursoNaoEncontradoException(
                    String.format(MessageConstants.MENSAGEM_SOLICITACAO_CADASTRO_NAO_ENCONTRADA, id));
        }

        return optionalSolicitacao.get();
    }

    public List<Solicitacao> buscaSolicitacoesDoClienteNoIntervalo(Cliente cliente, String dataInicial, String dataFinal){

        Date dataInicialConvertida = DateUtil.stringToDate(dataInicial);
        Date dataFinalConvertida = DateUtil.stringToDate(dataFinal);

        return this.solicitacaoCadastroRepository.findByClienteAndDataCriacaoBetween(cliente,
                dataInicialConvertida, dataFinalConvertida);
    }

    public Solicitacao registraSolicitacaoCadastroCliente(Cliente cliente){

        try {

            Solicitacao solicitacaoCliente = new Solicitacao();

            solicitacaoCliente.setCliente(cliente);
            solicitacaoCliente.setDataCriacao(new Date());
            solicitacaoCliente.setStatus(StatusSolicitacao.EM_ANALISE_DOCUMENTOS);
            solicitacaoCliente.setNomeImagemCpfCnpj(cliente.getNomeImagemCpfCnpj());
            solicitacaoCliente.setNomeImagemRg(cliente.getNomeImagemRg());
            solicitacaoCliente.setLatitude(cliente.getLatitude());
            solicitacaoCliente.setLongitude(cliente.getLongitude());

            Solicitacao solicitacaoSalva = solicitacaoCadastroRepository.save(solicitacaoCliente);
            return solicitacaoSalva;

        }
        catch (Exception e){

            logger.error(String.format(MessageConstants.MENSAGEM_ERRO_AO_PERSISTIR_SOLICITACAO_CADASTRO_CLIENTE, cliente.getCpfCnpj()), e);
            throw new PersistenceException(String.format(MessageConstants.MENSAGEM_ERRO_AO_PERSISTIR_SOLICITACAO_CADASTRO_CLIENTE,
                    cliente.getCpfCnpj()));
        }
    }

    public Solicitacao atualizacaoParcialSolicitacaoCliente(Long id, String status){

        Solicitacao solicitacaoNoBanco = this.buscaSolicitacaoPorId(id);

        if(!StringUtils.isEmpty(status)) solicitacaoNoBanco.setStatus(StatusSolicitacao.valueOf(status));

        Solicitacao solicitacaoSalva = this.solicitacaoCadastroRepository.save(solicitacaoNoBanco);
        return solicitacaoSalva;
    }
}
