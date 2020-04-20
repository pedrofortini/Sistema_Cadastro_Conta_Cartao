package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ClienteRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-20T00:37:01.854-03:00")

public class ClienteRequest   {
  @JsonProperty("nome")
  private String nome = null;

  @JsonProperty("cpf_cnpj")
  private String cpfCnpj = null;

  @JsonProperty("numero_rg")
  private String numeroRg = null;

  @JsonProperty("cidade")
  private String cidade = null;

  @JsonProperty("estado")
  private String estado = null;

  @JsonProperty("pais")
  private String pais = null;

  @JsonProperty("latitude")
  private Double latitude = null;

  @JsonProperty("longitude")
  private Double longitude = null;

  @JsonProperty("nome_imagem_rg")
  private String nomeImagemRg = null;

  @JsonProperty("nome_imagem_cpf_cnpj")
  private String nomeImagemCpfCnpj = null;

  public ClienteRequest nome(String nome) {
    this.nome = nome;
    return this;
  }

   /**
   * Nome Completo do Cliente
   * @return nome
  **/
  @ApiModelProperty(required = true, value = "Nome Completo do Cliente")
  @NotNull

 @Size(min=1)
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public ClienteRequest cpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
    return this;
  }

   /**
   * CPF/CNPJ do Cliente
   * @return cpfCnpj
  **/
  @ApiModelProperty(required = true, value = "CPF/CNPJ do Cliente")
  @NotNull

 @Size(min=11,max=14)
  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public ClienteRequest numeroRg(String numeroRg) {
    this.numeroRg = numeroRg;
    return this;
  }

   /**
   * Número do RG do Cliente
   * @return numeroRg
  **/
  @ApiModelProperty(required = true, value = "Número do RG do Cliente")
  @NotNull

 @Size(min=1)
  public String getNumeroRg() {
    return numeroRg;
  }

  public void setNumeroRg(String numeroRg) {
    this.numeroRg = numeroRg;
  }

  public ClienteRequest cidade(String cidade) {
    this.cidade = cidade;
    return this;
  }

   /**
   * Cidade do Cliente
   * @return cidade
  **/
  @ApiModelProperty(required = true, value = "Cidade do Cliente")
  @NotNull

 @Size(min=1)
  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public ClienteRequest estado(String estado) {
    this.estado = estado;
    return this;
  }

   /**
   * Estado do Cliente
   * @return estado
  **/
  @ApiModelProperty(required = true, value = "Estado do Cliente")
  @NotNull

 @Size(min=1)
  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public ClienteRequest pais(String pais) {
    this.pais = pais;
    return this;
  }

   /**
   * País do Cliente
   * @return pais
  **/
  @ApiModelProperty(required = true, value = "País do Cliente")
  @NotNull

 @Size(min=1)
  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public ClienteRequest latitude(Double latitude) {
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

  public ClienteRequest longitude(Double longitude) {
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

  public ClienteRequest nomeImagemRg(String nomeImagemRg) {
    this.nomeImagemRg = nomeImagemRg;
    return this;
  }

   /**
   * Nome da imagem do RG do Cliente salva no S3
   * @return nomeImagemRg
  **/
  @ApiModelProperty(required = true, value = "Nome da imagem do RG do Cliente salva no S3")
  @NotNull

 @Size(min=1)
  public String getNomeImagemRg() {
    return nomeImagemRg;
  }

  public void setNomeImagemRg(String nomeImagemRg) {
    this.nomeImagemRg = nomeImagemRg;
  }

  public ClienteRequest nomeImagemCpfCnpj(String nomeImagemCpfCnpj) {
    this.nomeImagemCpfCnpj = nomeImagemCpfCnpj;
    return this;
  }

   /**
   * Nome da imagem do CPF do Cliente salva no S3
   * @return nomeImagemCpfCnpj
  **/
  @ApiModelProperty(required = true, value = "Nome da imagem do CPF do Cliente salva no S3")
  @NotNull

 @Size(min=1)
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
    ClienteRequest clienteRequest = (ClienteRequest) o;
    return Objects.equals(this.nome, clienteRequest.nome) &&
        Objects.equals(this.cpfCnpj, clienteRequest.cpfCnpj) &&
        Objects.equals(this.numeroRg, clienteRequest.numeroRg) &&
        Objects.equals(this.cidade, clienteRequest.cidade) &&
        Objects.equals(this.estado, clienteRequest.estado) &&
        Objects.equals(this.pais, clienteRequest.pais) &&
        Objects.equals(this.latitude, clienteRequest.latitude) &&
        Objects.equals(this.longitude, clienteRequest.longitude) &&
        Objects.equals(this.nomeImagemRg, clienteRequest.nomeImagemRg) &&
        Objects.equals(this.nomeImagemCpfCnpj, clienteRequest.nomeImagemCpfCnpj);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, cpfCnpj, numeroRg, cidade, estado, pais, latitude, longitude, nomeImagemRg, nomeImagemCpfCnpj);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClienteRequest {\n");
    
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cpfCnpj: ").append(toIndentedString(cpfCnpj)).append("\n");
    sb.append("    numeroRg: ").append(toIndentedString(numeroRg)).append("\n");
    sb.append("    cidade: ").append(toIndentedString(cidade)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    pais: ").append(toIndentedString(pais)).append("\n");
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

