package one.digitalinnovation.gof.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import one.digitalinnovation.gof.exception.response.ErrorResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BusinessException extends Exception {


  private ErrorResponse errorResponse;


  public BusinessException(String message, ErrorResponse errorResponse) {
    super(message);
    this.errorResponse = errorResponse;
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
    this.errorResponse = new ErrorResponse("422", message, "", LocalDateTime.now());
  }

  public BusinessException(String errorCode, String message, String details) {
    super(message);
    this.errorResponse = new ErrorResponse(errorCode, message, details, LocalDateTime.now());
  }

  public BusinessException(String errorCode, String message, String details, Throwable cause) {
    super(message, cause);
    this.errorResponse = new ErrorResponse(errorCode, message, details, LocalDateTime.now());
  }

  public BusinessException(String s) {
    super(s);
  }
}
