package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets StatusSolicitacao
 */
public enum StatusSolicitacao {
  
  APROVADA("APROVADA"),
  
  REPROVADA("REPROVADA"),
  
  EM_ANALISE_DOCUMENTOS("EM_ANALISE_DOCUMENTOS"),
  
  EM_ANALISE_FRAUDE("EM_ANALISE_FRAUDE"),
  
  EM_ANALISE_CREDITO("EM_ANALISE_CREDITO");

  private String value;

  StatusSolicitacao(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StatusSolicitacao fromValue(String text) {
    for (StatusSolicitacao b : StatusSolicitacao.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

