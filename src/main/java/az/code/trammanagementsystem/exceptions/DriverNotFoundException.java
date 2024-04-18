package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
public class DriverNotFoundException extends DriverException {

    public DriverNotFoundException() {
        super(ErrorInfo.builder().status(404).message("Driver not found").build());
    }
}
