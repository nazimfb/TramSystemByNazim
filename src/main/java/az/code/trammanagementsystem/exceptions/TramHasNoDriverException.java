package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class TramHasNoDriverException extends TramException {
    public TramHasNoDriverException() {
        super(ErrorInfo.builder().status(400).message("Tram has no driver").build());
    }
}
