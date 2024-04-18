package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;

public class InvalidStopFormatException extends StopException {
    public InvalidStopFormatException(String message) {
        super(ErrorInfo.builder().status(400).message(message).build());
    }
}
