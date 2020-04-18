package com.cadastro.cliente.api.infrastructure.persistence;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import com.cadastro.cliente.api.domain.solicitacaocadastro.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SolicitacaoCadastroRepository extends JpaRepository<Solicitacao, Long> {

    List<Solicitacao> findByClienteAndDataCriacaoBetween(Cliente cliente, Date dataInicial, Date dataFinal);
}
