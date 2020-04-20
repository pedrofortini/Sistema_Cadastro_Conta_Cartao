package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ClienteResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-20T00:37:01.854-03:00")

public class ClienteResponse   {
  @JsonProperty("nome")
  private String nome = null;

  @JsonProperty("cpf_cnpj")
  private String cpfCnpj = null;

  @JsonProperty("numero_rg")
  private String numeroRg = null;

  @JsonProperty("conta_cartao")
  private String contaCartao = null;

  @JsonProperty("status_criacao_conta")
  private String statusCriacaoConta = null;

  @JsonProperty("cidade")
  private String cidade = null;

  @JsonProperty("estado")
  private String estado = null;

  @JsonProperty("pais")
  private String pais = null;

  public ClienteResponse nome(String nome) {
    this.nome = nome;
    return this;
  }

   /**
   * Nome Completo do Cliente
   * @return nome
  **/
  @ApiModelProperty(value = "Nome Completo do Cliente")


  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public ClienteResponse cpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
    return this;
  }

   /**
   * CPF/CNPJ do Cliente
   * @return cpfCnpj
  **/
  @ApiModelProperty(value = "CPF/CNPJ do Cliente")


  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public ClienteResponse numeroRg(String numeroRg) {
    this.numeroRg = numeroRg;
    return this;
  }

   /**
   * Número do RG do Cliente
   * @return numeroRg
  **/
  @ApiModelProperty(value = "Número do RG do Cliente")


  public String getNumeroRg() {
    return numeroRg;
  }

  public void setNumeroRg(String numeroRg) {
    this.numeroRg = numeroRg;
  }

  public ClienteResponse contaCartao(String contaCartao) {
    this.contaCartao = contaCartao;
    return this;
  }

   /**
   * Conta Cartão do Cliente
   * @return contaCartao
  **/
  @ApiModelProperty(value = "Conta Cartão do Cliente")


  public String getContaCartao() {
    return contaCartao;
  }

  public void setContaCartao(String contaCartao) {
    this.contaCartao = contaCartao;
  }

  public ClienteResponse statusCriacaoConta(String statusCriacaoConta) {
    this.statusCriacaoConta = statusCriacaoConta;
    return this;
  }

   /**
   * Status do Cadastro do Cliente
   * @return statusCriacaoConta
  **/
  @ApiModelProperty(value = "Status do Cadastro do Cliente")


  public String getStatusCriacaoConta() {
    return statusCriacaoConta;
  }

  public void setStatusCriacaoConta(String statusCriacaoConta) {
    this.statusCriacaoConta = statusCriacaoConta;
  }

  public ClienteResponse cidade(String cidade) {
    this.cidade = cidade;
    return this;
  }

   /**
   * Cidade do Cliente
   * @return cidade
  **/
  @ApiModelProperty(value = "Cidade do Cliente")


  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public ClienteResponse estado(String estado) {
    this.estado = estado;
    return this;
  }

   /**
   * Estado do Cliente
   * @return estado
  **/
  @ApiModelProperty(value = "Estado do Cliente")


  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public ClienteResponse pais(String pais) {
    this.pais = pais;
    return this;
  }

   /**
   * País do Cliente
   * @return pais
  **/
  @ApiModelProperty(value = "País do Cliente")


  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClienteResponse clienteResponse = (ClienteResponse) o;
    return Objects.equals(this.nome, clienteResponse.nome) &&
        Objects.equals(this.cpfCnpj, clienteResponse.cpfCnpj) &&
        Objects.equals(this.numeroRg, clienteResponse.numeroRg) &&
        Objects.equals(this.contaCartao, clienteResponse.contaCartao) &&
        Objects.equals(this.statusCriacaoConta, clienteResponse.statusCriacaoConta) &&
        Objects.equals(this.cidade, clienteResponse.cidade) &&
        Objects.equals(this.estado, clienteResponse.estado) &&
        Objects.equals(this.pais, clienteResponse.pais);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, cpfCnpj, numeroRg, contaCartao, statusCriacaoConta, cidade, estado, pais);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClienteResponse {\n");
    
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cpfCnpj: ").append(toIndentedString(cpfCnpj)).append("\n");
    sb.append("    numeroRg: ").append(toIndentedString(numeroRg)).append("\n");
    sb.append("    contaCartao: ").append(toIndentedString(contaCartao)).append("\n");
    sb.append("    statusCriacaoConta: ").append(toIndentedString(statusCriacaoConta)).append("\n");
    sb.append("    cidade: ").append(toIndentedString(cidade)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    pais: ").append(toIndentedString(pais)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

