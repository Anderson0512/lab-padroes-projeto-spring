package one.digitalinnovation.gof.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import one.digitalinnovation.gof.model.pagination.SimplePageable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ClientsResponseDTO implements Serializable {
  @Serial
  static final long serialVersionUID = -3387516993124229948L;

  @JsonProperty("_pageable")
  SimplePageable pageable = new SimplePageable();

  @JsonProperty("_content")
  private List<ClientsDTO> content;
}
