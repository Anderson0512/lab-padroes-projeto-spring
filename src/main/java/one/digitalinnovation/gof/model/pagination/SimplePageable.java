package one.digitalinnovation.gof.model.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimplePageable {
  @JsonProperty("_moreElements")
  boolean moreElements;

  public SimplePageable() {
    this.moreElements = Boolean.FALSE;
  }
}
