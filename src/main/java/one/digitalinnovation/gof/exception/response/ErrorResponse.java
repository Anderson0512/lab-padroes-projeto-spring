package one.digitalinnovation.gof.exception.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {
  static final long serialVersionUID = -3387516993124229948L;

  @JsonProperty("_errorCode")
  private String errorCode;

  @JsonProperty("_message")
  private String message;

  @JsonProperty("_details")
  private String details;

  @JsonProperty("_timestamp")
  private LocalDateTime timestamp;
}
