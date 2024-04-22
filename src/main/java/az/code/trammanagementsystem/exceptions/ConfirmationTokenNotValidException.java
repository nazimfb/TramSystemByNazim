package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfirmationTokenNotValidException extends RuntimeException {
    private ErrorInfo errorInfo;

    public ConfirmationTokenNotValidException() {
        super("Link not valid");
        this.errorInfo = ErrorInfo.builder().status(400).message("Link not valid").build();
    }

    public ConfirmationTokenNotValidException(String message) {
        super(message);
        this.errorInfo = ErrorInfo.builder().status(400).message(message).build();
    }
}
