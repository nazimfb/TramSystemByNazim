package az.code.trammanagementsystem.exceptions.exceptionHandlers;

import az.code.trammanagementsystem.dto.ErrorInfo;
import az.code.trammanagementsystem.exceptions.DriverNotFoundException;
import az.code.trammanagementsystem.exceptions.InvalidDriverFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class DriverExceptionController {
    @ExceptionHandler(value = DriverNotFoundException.class)
    private ResponseEntity<ErrorInfo> exception(DriverNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorInfo(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = InvalidDriverFormatException.class)
    private ResponseEntity<List<ErrorInfo>> exception(InvalidDriverFormatException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
