package az.code.trammanagementsystem.exceptions.exceptionHandlers;

import az.code.trammanagementsystem.dto.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage() != null
                        ? error.getDefaultMessage()
                        : "Validation error occurred.")
                .toList().getFirst();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorInfo.builder()
                .status(400).message(errorMessage).build());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorInfo> handleValidationExceptions(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorInfo.builder().status(400).message(ex.getMessage()).build());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorInfo> handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorInfo.builder().status(400).message(ex.getMessage()).build());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> handleHttpExceptions(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(
                ErrorInfo.builder().status(400).message(exception.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }
}
