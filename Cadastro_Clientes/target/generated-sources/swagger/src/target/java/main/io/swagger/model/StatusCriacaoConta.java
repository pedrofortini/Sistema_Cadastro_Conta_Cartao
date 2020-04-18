package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets StatusCriacaoConta
 */
public enum StatusCriacaoConta {
  
  CRIADA("CRIADA"),
  
  ANALISE_PENDENTE("ANALISE_PENDENTE"),
  
  REJEITADA("REJEITADA");

  private String value;

  StatusCriacaoConta(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StatusCriacaoConta fromValue(String text) {
    for (StatusCriacaoConta b : StatusCriacaoConta.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

