package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TramException extends RuntimeException {

    private final ErrorInfo errorInfo;

    public TramException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
