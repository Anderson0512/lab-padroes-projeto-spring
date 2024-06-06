package one.digitalinnovation.gof.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientDTO {

  private String name;
  private String profession;
  private String age;
  private String zipCode;
}
