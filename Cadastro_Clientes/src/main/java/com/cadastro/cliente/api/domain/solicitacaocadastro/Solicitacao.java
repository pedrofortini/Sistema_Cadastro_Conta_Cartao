package com.cadastro.cliente.api.domain.solicitacaocadastro;


import com.cadastro.cliente.api.domain.cliente.Cliente;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "solicitacao_cadastro_conta_cartao")
@EnableAutoConfiguration
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

    @Column(name="status_conta_conta")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNomeImagemRg() {
        return nomeImagemRg;
    }

    public void setNomeImagemRg(String nomeImagemRg) {
        this.nomeImagemRg = nomeImagemRg;
    }

    public String getNomeImagemCpfCnpj() {
        return nomeImagemCpfCnpj;
    }

    public void setNomeImagemCpfCnpj(String nomeImagemCpfCnpj) {
        this.nomeImagemCpfCnpj = nomeImagemCpfCnpj;
    }
}
