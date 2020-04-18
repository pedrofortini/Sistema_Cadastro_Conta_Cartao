package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SolicitacaoCadastroResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-18T17:16:52.388-03:00")

public class SolicitacaoCadastroResponse   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("data_criacao")
  private String dataCriacao = null;

  @JsonProperty("cpf_cnpj_cliente")
  private String cpfCnpjCliente = null;

  @JsonProperty("status_solicitacao")
  private String statusSolicitacao = null;

  @JsonProperty("latitude")
  private Double latitude = null;

  @JsonProperty("longitude")
  private Double longitude = null;

  @JsonProperty("nome_imagem_rg")
  private String nomeImagemRg = null;

  @JsonProperty("nome_imagem_cpf_cnpj")
  private String nomeImagemCpfCnpj = null;

  public SolicitacaoCadastroResponse id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Id da Solicitaçao
   * @return id
  **/
  @ApiModelProperty(value = "Id da Solicitaçao")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SolicitacaoCadastroResponse dataCriacao(String dataCriacao) {
    this.dataCriacao = dataCriacao;
    return this;
  }

   /**
   * Data de Criação da Solicitação
   * @return dataCriacao
  **/
  @ApiModelProperty(value = "Data de Criação da Solicitação")


  public String getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(String dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public SolicitacaoCadastroResponse cpfCnpjCliente(String cpfCnpjCliente) {
    this.cpfCnpjCliente = cpfCnpjCliente;
    return this;
  }

   /**
   * CPF/CNPJ do Cliente
   * @return cpfCnpjCliente
  **/
  @ApiModelProperty(value = "CPF/CNPJ do Cliente")


  public String getCpfCnpjCliente() {
    return cpfCnpjCliente;
  }

  public void setCpfCnpjCliente(String cpfCnpjCliente) {
    this.cpfCnpjCliente = cpfCnpjCliente;
  }

  public SolicitacaoCadastroResponse statusSolicitacao(String statusSolicitacao) {
    this.statusSolicitacao = statusSolicitacao;
    return this;
  }

   /**
   * Status da Solicitação
   * @return statusSolicitacao
  **/
  @ApiModelProperty(value = "Status da Solicitação")


  public String getStatusSolicitacao() {
    return statusSolicitacao;
  }

  public void setStatusSolicitacao(String statusSolicitacao) {
    this.statusSolicitacao = statusSolicitacao;
  }

  public SolicitacaoCadastroResponse latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

   /**
   * Latitude do Cliente no momento da solicitação
   * @return latitude
  **/
  @ApiModelProperty(value = "Latitude do Cliente no momento da solicitação")


  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public SolicitacaoCadastroResponse longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

   /**
   * Longitude do Cliente no momento da solicitação
   * @return longitude
  **/
  @ApiModelProperty(value = "Longitude do Cliente no momento da solicitação")


  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public SolicitacaoCadastroResponse nomeImagemRg(String nomeImagemRg) {
    this.nomeImagemRg = nomeImagemRg;
    return this;
  }

   /**
   * Nome da imagem do RG do Cliente salva no S3
   * @return nomeImagemRg
  **/
  @ApiModelProperty(value = "Nome da imagem do RG do Cliente salva no S3")


  public String getNomeImagemRg() {
    return nomeImagemRg;
  }

  public void setNomeImagemRg(String nomeImagemRg) {
    this.nomeImagemRg = nomeImagemRg;
  }

  public SolicitacaoCadastroResponse nomeImagemCpfCnpj(String nomeImagemCpfCnpj) {
    this.nomeImagemCpfCnpj = nomeImagemCpfCnpj;
    return this;
  }

   /**
   * Nome da imagem do CPF do Cliente salva no S3
   * @return nomeImagemCpfCnpj
  **/
  @ApiModelProperty(value = "Nome da imagem do CPF do Cliente salva no S3")


  public String getNomeImagemCpfCnpj() {
    return nomeImagemCpfCnpj;
  }

  public void setNomeImagemCpfCnpj(String nomeImagemCpfCnpj) {
    this.nomeImagemCpfCnpj = nomeImagemCpfCnpj;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SolicitacaoCadastroResponse solicitacaoCadastroResponse = (SolicitacaoCadastroResponse) o;
    return Objects.equals(this.id, solicitacaoCadastroResponse.id) &&
        Objects.equals(this.dataCriacao, solicitacaoCadastroResponse.dataCriacao) &&
        Objects.equals(this.cpfCnpjCliente, solicitacaoCadastroResponse.cpfCnpjCliente) &&
        Objects.equals(this.statusSolicitacao, solicitacaoCadastroResponse.statusSolicitacao) &&
        Objects.equals(this.latitude, solicitacaoCadastroResponse.latitude) &&
        Objects.equals(this.longitude, solicitacaoCadastroResponse.longitude) &&
        Objects.equals(this.nomeImagemRg, solicitacaoCadastroResponse.nomeImagemRg) &&
        Objects.equals(this.nomeImagemCpfCnpj, solicitacaoCadastroResponse.nomeImagemCpfCnpj);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dataCriacao, cpfCnpjCliente, statusSolicitacao, latitude, longitude, nomeImagemRg, nomeImagemCpfCnpj);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SolicitacaoCadastroResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dataCriacao: ").append(toIndentedString(dataCriacao)).append("\n");
    sb.append("    cpfCnpjCliente: ").append(toIndentedString(cpfCnpjCliente)).append("\n");
    sb.append("    statusSolicitacao: ").append(toIndentedString(statusSolicitacao)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    nomeImagemRg: ").append(toIndentedString(nomeImagemRg)).append("\n");
    sb.append("    nomeImagemCpfCnpj: ").append(toIndentedString(nomeImagemCpfCnpj)).append("\n");
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

