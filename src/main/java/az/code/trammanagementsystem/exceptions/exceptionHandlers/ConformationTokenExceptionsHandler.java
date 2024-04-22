package az.code.trammanagementsystem.exceptions.exceptionHandlers;

import az.code.trammanagementsystem.dto.ErrorInfo;
import az.code.trammanagementsystem.exceptions.ConfirmationTokenExpiredException;
import az.code.trammanagementsystem.exceptions.ConfirmationTokenNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConformationTokenExceptionsHandler {
    @ExceptionHandler(value = ConfirmationTokenExpiredException.class)
    private ResponseEntity<ErrorInfo> exception(ConfirmationTokenExpiredException exception) {
        return new ResponseEntity<>(
                exception.getErrorInfo(),
                HttpStatus.valueOf(exception.getErrorInfo().getStatus()));
    }

    @ExceptionHandler(value = ConfirmationTokenNotValidException.class)
    private ResponseEntity<ErrorInfo> exception(ConfirmationTokenNotValidException exception) {
        return new ResponseEntity<>(
                exception.getErrorInfo(),
                HttpStatus.valueOf(exception.getErrorInfo().getStatus()));
    }
}
