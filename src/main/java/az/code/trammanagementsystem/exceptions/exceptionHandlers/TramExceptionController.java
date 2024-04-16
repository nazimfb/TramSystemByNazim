package az.code.trammanagementsystem.exceptions.exceptionHandlers;

import az.code.trammanagementsystem.dto.ErrorInfo;
import az.code.trammanagementsystem.exceptions.TramNotFoundException;
import com.fasterxml.jackson.core.JacksonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TramExceptionController {
    @ExceptionHandler(value = TramNotFoundException.class)
    public ResponseEntity<ErrorInfo> exception(TramNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorInfo(), HttpStatus.NOT_FOUND);
    }
}
