package one.digitalinnovation.gof.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CepException extends Exception {

    private String erroCode;
    private String message;
    private String details;

    public CepException(String errorCode, String message, String details) {
        super(message);
        this.erroCode = errorCode;
        this.message = message;
        this.details = details;
    }
}
