package com.cadastro.cliente.api.domain.cliente;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "cliente")
@EnableAutoConfiguration
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

    @Column(name="status_conta_conta")
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


    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroRG() {
        return numeroRG;
    }

    public void setNumeroRG(String numeroRG) {
        this.numeroRG = numeroRG;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public StatusContaCartao getStatusContaCartao() {
        return statusContaCartao;
    }

    public void setStatusContaCartao(StatusContaCartao statusContaCartao) {
        this.statusContaCartao = statusContaCartao;
    }

    public String getContaCartao() {
        return contaCartao;
    }

    public void setContaCartao(String contaCartao) {
        this.contaCartao = contaCartao;
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
}
