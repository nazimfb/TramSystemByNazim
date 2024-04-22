package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;

public class TramMustNotBeOnRouteException extends TramException {
    public TramMustNotBeOnRouteException() {
        super(ErrorInfo.builder().status(400).message("Tram must not be on route").build());
    }

    public TramMustNotBeOnRouteException(String message) {
        super(ErrorInfo.builder().status(400).message(message).build());
    }
}
