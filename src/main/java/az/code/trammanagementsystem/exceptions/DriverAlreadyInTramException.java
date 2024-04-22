package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class DriverAlreadyInTramException extends DriverException {
    public DriverAlreadyInTramException() {
        super(ErrorInfo.builder().status(409).message("Driver is already in a tram").build());
    }
}
