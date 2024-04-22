package az.code.trammanagementsystem.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UserAlreadyExistsException extends AuthException {
    public UserAlreadyExistsException() {
        super(409, "User already exists");
    }
}
