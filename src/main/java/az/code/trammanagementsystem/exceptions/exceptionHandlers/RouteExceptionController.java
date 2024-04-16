package az.code.trammanagementsystem.exceptions.exceptionHandlers;

import az.code.trammanagementsystem.exceptions.InvalidRouteFormatException;
import az.code.trammanagementsystem.exceptions.RouteNotFoundException;
import az.code.trammanagementsystem.exceptions.TramAlreadyOnRouteException;
import az.code.trammanagementsystem.exceptions.TramNotAssignedToRouteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RouteExceptionController {
    @ExceptionHandler(value = RouteNotFoundException.class)
    public ResponseEntity<Object> exception(RouteNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidRouteFormatException.class)
    public ResponseEntity<Object> exception(InvalidRouteFormatException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TramAlreadyOnRouteException.class)
    public ResponseEntity<Object> exception(TramAlreadyOnRouteException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TramNotAssignedToRouteException.class)
    public ResponseEntity<Object> exception(TramNotAssignedToRouteException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
