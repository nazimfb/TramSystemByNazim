package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
public class InvalidDriverFormatException extends DriverException {
    public InvalidDriverFormatException(String message) {
        super((ErrorInfo.builder().status(400).message(message).build()));
    }
}
