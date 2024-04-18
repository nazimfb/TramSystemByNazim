package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class TramNotFoundException extends TramException {

    public TramNotFoundException() {
        super(ErrorInfo.builder().status(404).message("Tram not found").build());
    }
}
