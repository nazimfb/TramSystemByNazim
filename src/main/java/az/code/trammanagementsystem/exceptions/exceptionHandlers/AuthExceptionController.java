package az.code.trammanagementsystem.exceptions.exceptionHandlers;

import az.code.trammanagementsystem.dto.ErrorInfo;
import az.code.trammanagementsystem.exceptions.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionController {
    @ExceptionHandler(value = AuthException.class)
    private ResponseEntity<ErrorInfo> exception(AuthException exception) {
        return new ResponseEntity<>(
                exception.getErrorInfo(),
                HttpStatus.valueOf(exception.getErrorInfo().getStatus()));
    }
}
