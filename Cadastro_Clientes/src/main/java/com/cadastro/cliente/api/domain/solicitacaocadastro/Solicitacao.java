package com.cadastro.cliente.api.domain.solicitacaocadastro;


import com.cadastro.cliente.api.domain.cliente.Cliente;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "solicitacao_cadastro_conta_cartao")
@EnableAutoConfiguration
@Data
public class Solicitacao {

    private static final long serialVersionUID = 5113349532609550728L;

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @NotNull
    private Cliente cliente;

    @Column(name="data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dataCriacao;

    @Column(name="status_solicitacao")
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusSolicitacao status;

    @Column(name="latitude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;

    @Column(name="nome_imagem_rg")
    @NotNull
    private String nomeImagemRg;

    @Column(name="nome_imagem_cpf_cnpj")
    @NotNull
    private String nomeImagemCpfCnpj;
}
