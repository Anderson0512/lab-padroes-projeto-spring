package one.digitalinnovation.gof.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ClientsDTO implements Serializable {

  @Serial
  static final long serialVersionUID = -3387516993124229948L;

  private String id;
  private String name;
}
