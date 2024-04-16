package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;

import java.io.Serial;


public class DriverNotFoundException extends RuntimeException {
    private ErrorInfo errorInfo;
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }
}
