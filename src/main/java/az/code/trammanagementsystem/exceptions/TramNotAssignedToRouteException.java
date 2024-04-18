package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class TramNotAssignedToRouteException extends TramException {
    public TramNotAssignedToRouteException(String message) {
        super(ErrorInfo.builder().status(400).message(message).build());
    }
}
