package az.code.trammanagementsystem.exceptions;

public class UserAlreadyVerifiedException extends AuthException {
    public UserAlreadyVerifiedException(String confirmedEmail) {
        super(409, "User with email " + confirmedEmail + " is already verified");
    }
}
