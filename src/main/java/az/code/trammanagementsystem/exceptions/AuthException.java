package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ErrorInfo errorInfo;

    public AuthException(String message) {
        this.errorInfo = ErrorInfo.builder().status(400).message(message).build();
    }

    public AuthException(Integer statusCode, String message) {
        this.errorInfo = ErrorInfo.builder().status(statusCode).message(message).build();
    }
}
