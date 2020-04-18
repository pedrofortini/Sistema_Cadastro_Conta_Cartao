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

    @Column(name="time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date timeStamp;

    @Column(name="status_conta_conta")
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusSolicitacao status;

    @Column(name="latitude")
    @NotNull
    private Double latitude;

    @Column(name="longitude")
    @NotNull
    private Double longitude;

    @Column(name="link_s3_imagem_rg")
    @NotNull
    private String linkS3ImagemRg;

    @Column(name="link_s3_imagem_cpf")
    @NotNull
    private String linkS3ImagemCpf;


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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getLinkS3ImagemRg() {
        return linkS3ImagemRg;
    }

    public void setLinkS3ImagemRg(String linkS3ImagemRg) {
        this.linkS3ImagemRg = linkS3ImagemRg;
    }

    public String getLinkS3ImagemCpf() {
        return linkS3ImagemCpf;
    }

    public void setLinkS3ImagemCpf(String linkS3ImagemCpf) {
        this.linkS3ImagemCpf = linkS3ImagemCpf;
    }
}
