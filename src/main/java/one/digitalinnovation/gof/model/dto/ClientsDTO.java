package one.digitalinnovation.gof.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientsDTO implements Serializable {

  static final long serialVersionUID = -3387516993124229948L;

  private String id;
  private String name;
}
