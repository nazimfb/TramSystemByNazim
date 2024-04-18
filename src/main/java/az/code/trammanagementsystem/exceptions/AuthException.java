package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthException extends RuntimeException {
    private final ErrorInfo errorInfo;

    public AuthException(String message) {
        this.errorInfo = ErrorInfo.builder().status(400).message(message).build();
    }
    public AuthException(Integer statusCode, String message) {
        this.errorInfo = ErrorInfo.builder().status(statusCode).message(message).build();
    }
}
