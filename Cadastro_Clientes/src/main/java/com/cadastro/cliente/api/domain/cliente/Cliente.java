package com.cadastro.cliente.api.domain.cliente;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "cliente")
@EnableAutoConfiguration
@Data
public class Cliente implements Serializable {

    private static final long serialVersionUID = -8538644326772769384L;

    @Id
    @Column(name="cpf_cnpj")
    @NotNull
    private String cpfCnpj;

    @Column(name="nome")
    @NotNull
    private String nome;

    @Column(name="numero_rg")
    @NotNull
    private String numeroRG;

    @Column(name="cidade")
    @NotNull
    private String cidade;

    @Column(name="estado")
    @NotNull
    private String estado;

    @Column(name="pais")
    @NotNull
    private String pais;

    @Column(name="status_conta_cartao")
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusContaCartao statusContaCartao;

    @Column(name="conta_cartao")
    private String contaCartao;

    @Transient
    private String nomeImagemRg;

    @Transient
    private String nomeImagemCpfCnpj;

    @Transient
    private Double latitude;

    @Transient
    private Double longitude;
}
