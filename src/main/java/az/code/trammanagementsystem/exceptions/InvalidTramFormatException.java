package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class InvalidTramFormatException extends TramException {
    public InvalidTramFormatException(String message) {
        super(ErrorInfo.builder().status(400).message(message).build());
    }
}
