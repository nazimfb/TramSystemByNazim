package az.code.trammanagementsystem.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UserNotFoundException extends AuthException {

    public UserNotFoundException() {
        super(404,"User Not Found");
    }
}
