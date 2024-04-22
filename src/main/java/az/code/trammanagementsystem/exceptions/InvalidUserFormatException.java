package az.code.trammanagementsystem.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class InvalidUserFormatException extends AuthException {
    public InvalidUserFormatException(String message) {
        super(400, message);
    }

    public InvalidUserFormatException(int statusCode, String message) {
        super(statusCode, message);
    }
}
