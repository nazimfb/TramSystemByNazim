package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class StopNotFoundException extends StopException {

    public StopNotFoundException() {
        super(ErrorInfo.builder().status(404).message("Stop not found").build());
    }
}
