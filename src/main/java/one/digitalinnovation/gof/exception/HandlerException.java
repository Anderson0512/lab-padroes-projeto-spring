package one.digitalinnovation.gof.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> business(BusinessException exception) {
        log.error(exception.getMessage(), exception);

        if ("400".equals(exception.getErrorResponse().getErrorCode())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrorResponse());
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getErrorResponse());
        }
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> runtime(RuntimeException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
